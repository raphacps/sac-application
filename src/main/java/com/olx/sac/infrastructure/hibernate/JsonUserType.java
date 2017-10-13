package com.olx.sac.infrastructure.hibernate;

import com.olx.sac.domain.model.event.eventstore.DomainEvent;
import com.olx.sac.infrastructure.json.JsonUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class JsonUserType implements UserType {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUserType.class);
    private Class<?> type;

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    public Object deepCopy(Object value) throws HibernateException {
        try {
            if (value == null) {
                return value;
            }
            return JsonUtil.toJson(value);
        } catch (Exception e) {
            LOGGER.error("Falha ao processar json", e);
            throw new RuntimeException(e);
        }
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return JsonUtil.toJson(value);
    }

    @Override
    public boolean equals(Object obj1, Object obj2) throws HibernateException {
        return Objects.equals(obj1, obj2 == null ? null : JsonUtil.toJson(obj2));
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        if (!rs.wasNull()) {
            if (rs.getString(names[0]) == null) {
                return null;
            }
            type = instantiateClassType(rs);
            return JsonUtil.fromJson(rs.getString(names[0]), type);
        }
        return null;
    }

    private Class<?> instantiateClassType(ResultSet rs) throws SQLException {
        Class<?> classType = null;
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
            try {
                classType = Class.forName(rs.getString(i));
                if (!classType.isInstance(DomainEvent.class)) {
                    continue;
                }
            } catch (ClassNotFoundException e) {
            }
        }
        if (classType == null) {
            throw new RuntimeException("Nao foi encontrada coluna na tabela que represente uma classe java");
        }
        return classType;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
            return;
        }
        st.setObject(index, value, Types.OTHER);
    }

    public boolean isMutable() {
        return true;
    }


    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    public Class returnedClass() {
        return type;
    }

    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }


}