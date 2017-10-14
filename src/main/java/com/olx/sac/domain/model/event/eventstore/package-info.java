/**
 * Created by raphael on 10/11/16.
 * Aggregates da camada de dominio
 */
@TypeDefs({@TypeDef(name = "jsonObject", typeClass = JsonUserType.class)})
package com.olx.sac.domain.model.event.eventstore;

import com.olx.sac.infrastructure.hibernate.JsonUserType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;