package com.sked.ofrestclient.entity.wrapper;

import com.sked.ofrestclient.entity.UserProperty;

import java.util.List;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 25-Jul-16.
 */
public class UserPropertiesWrapper {
    private List<UserProperty> property;

    public UserPropertiesWrapper() {
    }

    public UserPropertiesWrapper(List<UserProperty> property) {
        this.property = property;
    }

    public List<UserProperty> getProperty() {
        return property;
    }

    public void setProperty(List<UserProperty> property) {
        this.property = property;
    }
}
