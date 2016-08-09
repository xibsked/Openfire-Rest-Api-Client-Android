package com.sked.ofrestclient.entity.wrapper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 04-Aug-16.
 */
public class UserGroup {
    @SerializedName("groupname")
    private List<String> groups;

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
