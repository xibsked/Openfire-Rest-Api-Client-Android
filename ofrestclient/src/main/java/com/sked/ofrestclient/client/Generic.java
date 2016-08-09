package com.sked.ofrestclient.client;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

/**
 * XMPP Admin, All rights Reserved
 * Created by Sanjeet on 25-Jul-16.
 */
public class Generic<T> {
    private Class<T> clazz;

    public Generic(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T buildOne(JSONObject jsonObject) throws InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return clazz.getDeclaredConstructor(JSONObject.class).newInstance(jsonObject);
    }
}
