package com.sked.ofrestclient.client;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sked.ofrestclient.entity.AuthenticationToken;
import com.sked.ofrestclient.entity.GroupEntity;
import com.sked.ofrestclient.entity.RosterEntities;
import com.sked.ofrestclient.entity.RosterItemEntity;
import com.sked.ofrestclient.entity.UserEntities;
import com.sked.ofrestclient.entity.UserEntity;
import com.sked.ofrestclient.entity.wrapper.UserGroup;

import org.json.JSONObject;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 25-Jul-16.
 */
@SuppressWarnings("unused")
public class RestApiClient {

    private AuthenticationToken token;
    private String baseUrl;
    private Context context;
    private Gson gson;

    public RestApiClient(Context context, AuthenticationToken token, String baseUrl) {
        this.context = context;
        this.token = token;
        this.baseUrl = baseUrl;
        this.gson = new Gson();
    }

    public void getUsers(Response.Listener<UserEntities> listener, Response.ErrorListener error) {
        Request<UserEntities> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/", null, listener, error, UserEntities.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUsers(JSONObject params, Response.Listener<UserEntities> listener, Response.ErrorListener error) {
        Request<UserEntities> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/", (params == null) ? null : params.toString(), listener, error, UserEntities.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUser(String username, Response.Listener<UserEntity> listener, Response.ErrorListener error) {
        Request<UserEntity> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + username, null, listener, error, UserEntity.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createUser(UserEntity user, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(user);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateUser(UserEntity user, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(user);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "users/" + user.getUsername(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUser(String username, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + username, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getGroups(Response.Listener<GroupEntity> listener, Response.ErrorListener error) {
        Request<GroupEntity> request = new ApiRequest<>(Request.Method.GET, baseUrl + "groups/", null, listener, error, GroupEntity.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getGroup(String groupName, Response.Listener<GroupEntity> listener, Response.ErrorListener error) {
        Request<GroupEntity> request = new ApiRequest<>(Request.Method.GET, baseUrl + "groups/" + groupName, null, listener, error, GroupEntity.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createGroup(GroupEntity group, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(group);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "groups/", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateGroup(GroupEntity group, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(group);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "groups/" + group.getName(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteGroup(String groupName, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUserGroups(String userName, Response.Listener<UserGroup> listener, Response.ErrorListener error) {
        Request<UserGroup> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + userName + "/groups", null, listener, error, UserGroup.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToGroups(String userName, UserGroup groups, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(groups);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/groups", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToGroup(String userName, String groupName, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUserFromGroups(String userName, UserGroup groups, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(groups);
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/groups", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUserFromGroup(String userName, String groupName, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void lockoutUser(String userName, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "lockouts/" + userName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void unlockUser(String userName, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "lockouts/" + userName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUserRoster(String userName, Response.Listener<RosterEntities> listener, Response.ErrorListener error) {
        Request<RosterEntities> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + userName + "/roster", null, listener, error, RosterEntities.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToRoster(String userName, RosterItemEntity rosterItemEntity, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(rosterItemEntity);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/roster", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteFromRoster(String userName, String jid, Response.Listener<Status> listener, Response.ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/roster" + jid, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateRosterEntry(String userName, RosterItemEntity rosterItemEntity, Response.Listener<Status> listener, Response.ErrorListener error) {
        String params = gson.toJson(rosterItemEntity);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "users/" + userName + "/roster/" + rosterItemEntity.getJid(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }


}

