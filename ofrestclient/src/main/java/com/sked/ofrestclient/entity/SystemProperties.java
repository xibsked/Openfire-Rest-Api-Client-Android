package com.sked.ofrestclient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 13-Aug-16.
 */
public class SystemProperties {

    /**
     * The properties.
     */
    @JsonProperty("property")
    List<Property> properties;

    /**
     * Instantiates a new system properties.
     */
    public SystemProperties() {

    }

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    public List<Property> getProperties() {
        return properties;
    }


    /**
     * Sets the properties.
     *
     * @param properties the new properties
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
