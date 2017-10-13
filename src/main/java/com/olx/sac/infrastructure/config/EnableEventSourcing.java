package com.olx.sac.infrastructure.config;

import com.olx.sac.domain.model.event.eventstore.EventSourcingHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({EventSourcingHandler.class})
public @interface EnableEventSourcing {

}