package com.sked.ofrestclient.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 04-Aug-16.
 */
public class UserGroup implements Serializable {
    @JsonProperty("groupname")
    private List<String> groups;

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public List<String> asList() {
        return groups;
    }
}
