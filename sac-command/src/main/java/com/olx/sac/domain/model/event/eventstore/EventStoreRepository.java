package com.olx.sac.domain.model.event.eventstore;


import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public interface EventStoreRepository extends JpaRepository<EventStore, Long> {

}