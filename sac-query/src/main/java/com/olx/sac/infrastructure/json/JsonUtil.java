package com.olx.sac.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by raphael on 07/12/16.
 */
@Slf4j
public final class JsonUtil {

    private JsonUtil() {
    }

    public static <T> T fromJson(String json, Class<T> instance) {
        ObjectMapper objectMapper = new ObjectMapper();
        T obj = null;
        try {
            objectMapper = objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            obj = objectMapper.readValue(json, instance);
        } catch (IOException e) {
            log.error("Falha ao realizar bind do json", e);
            throw new JsonUtilException(e);
        }
        return obj;
    }

    public static String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper = objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .setSerializationInclusion(JsonInclude.Include.ALWAYS);
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Falha ao serializar objeto em json", e);
        }

        return "";
    }
}