package com.yto.tech.mail.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yto.tech.mail.Contant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T json2Object(String json, Class<T> objectClass) {
        Object o = null;
        Gson gson = new Gson();
        try {
            return (T) gson.fromJson(json, objectClass);
        } catch (Exception e) {
            LOG.error("erorr json: " + json, e);
        }
        return null;
    }

    public static String object2Json(Object objectClass) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonStr = gson.toJson(objectClass);
        return jsonStr;
    }




}

