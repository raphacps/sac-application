package com.olx.sac.domain.model.event.eventstore;


import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public interface EventStoreRepository extends JpaRepository<EventStore, Long> {
    List<EventStore<DomainEvent>> findByAggregateAndAggregateIdValorOrderByCreatedAt(String aggregate, String aggregateId);

    default <T> Optional<T> load(Class<T> aggregate, AggregateId aggregateId) {
        List<EventStore<DomainEvent>> eventStores = findByAggregateAndAggregateIdValorOrderByCreatedAt(aggregate.getCanonicalName(), aggregateId.asString());
        if (eventStores.isEmpty()) {
            return Optional.empty();
        }
        T aggregateInstance;
        try {
            Constructor<T> constructor = aggregate.getDeclaredConstructor();
            constructor.setAccessible(true);
            aggregateInstance = constructor.newInstance();
            T finalAggregateInstance = aggregateInstance;
            eventStores.stream()
                    .parallel()
                    .forEachOrdered(eventStore -> {
                        Arrays.stream(finalAggregateInstance.getClass().getDeclaredMethods())
                                .parallel()
                                .filter(methodWithEventSourcingHandlerAnnotation(eventStore.event())
                                        .and(methodWithSameEventName(eventStore.event())))
                                .forEachOrdered(method -> {
                                    try {
                                        method.invoke(finalAggregateInstance, eventStore.event());
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(aggregateInstance);
    }

    default <E> Predicate<Method> methodWithEventSourcingHandlerAnnotation(E event) {
        return method -> {
            method.setAccessible(true);
            return method.isAnnotationPresent(EventSourcingHandler.class);
        };

    }

    default <E> Predicate<Method> methodWithSameEventName(E event) {
        return method -> {
            method.setAccessible(true);
            return method.getParameterTypes()[0].getName().equals(event.getClass().getCanonicalName());
        };
    }

}