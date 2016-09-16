package com.sked.ofrestclient.client;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.sked.ofrestclient.entity.Account;
import com.sked.ofrestclient.entity.AuthenticationToken;
import com.sked.ofrestclient.entity.ChatRoom;
import com.sked.ofrestclient.entity.ChatRooms;
import com.sked.ofrestclient.entity.Group;
import com.sked.ofrestclient.entity.Groups;
import com.sked.ofrestclient.entity.Message;
import com.sked.ofrestclient.entity.Occupants;
import com.sked.ofrestclient.entity.Participants;
import com.sked.ofrestclient.entity.Property;
import com.sked.ofrestclient.entity.Roster;
import com.sked.ofrestclient.entity.Rosters;
import com.sked.ofrestclient.entity.Sessions;
import com.sked.ofrestclient.entity.Statistics;
import com.sked.ofrestclient.entity.SystemProperties;
import com.sked.ofrestclient.entity.User;
import com.sked.ofrestclient.entity.Users;
import com.sked.ofrestclient.entity.wrapper.UserGroup;

import org.json.JSONObject;

import static com.android.volley.Response.ErrorListener;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 25-Jul-16.
 */

public class RestApiClient {

    private AuthenticationToken token;
    private String baseUrl;
    private Context context;
    private Gson gson;
    private ObjectMapper mapper;
    private OnSuccess onSuccess;
    private OnFailure onFailure;

    private ObjectMapper getConfiguredMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
        return mapper;
    }

    public RestApiClient(Context context, Account account) {
        this.context = context;
        this.token = account.getAuthenticationToken();
        this.baseUrl = "http://" + account.getHost() + ":" + account.getPort() + "/plugins/restapi/v1/";
        this.gson = new Gson();
        this.mapper = getConfiguredMapper();
    }


    public RestApiClient(Context context) {
        this.context = context;
    }

    public RestApiClient account(Account account) {
        this.token = account.getAuthenticationToken();
        this.baseUrl = "http://" + account.getHost() + ":" + account.getPort() + "/plugins/restapi/v1/";
        this.gson = new Gson();
        this.mapper = getConfiguredMapper();
        return this;
    }

    public RestApiClient success(OnSuccess onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public RestApiClient failure(OnFailure onFailure) {
        this.onFailure = onFailure;
        return this;
    }

    public void getUsers(Listener<Users> listener, ErrorListener error) {
        Request<Users> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/",
                null, listener, error, Users.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUsers(JSONObject params, Listener<Users> listener,
                         ErrorListener error) {
        Request<Users> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/",
                (params == null) ? null : params.toString(), listener, error, Users.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUser(String username, Listener<User> listener,
                        ErrorListener error) {
        Request<User> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + username,
                null, listener, error, User.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createUser(User user, Listener<Status> listener,
                           ErrorListener error) {
        String params = gson.toJson(user);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/", params,
                listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateUser(User user, Listener<Status> listener,
                           ErrorListener error) {
        String params = gson.toJson(user);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "users/" + user.getUsername(),
                params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUser(String username, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + username, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getGroups(Listener<Groups> listener, ErrorListener error) {
        Request<Groups> request = new ApiRequest<>(Request.Method.GET, baseUrl + "groups/", null, listener, error, Groups.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getGroup(String groupName, Listener<Group> listener, ErrorListener error) {
        Request<Group> request = new ApiRequest<>(Request.Method.GET, baseUrl + "groups/" + groupName, null, listener, error, Group.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createGroup(Group group) {
        String params = gson.toJson(group);
        Request<Status> request = new ApiRequest<>(Request.Method.POST,
                baseUrl + "groups/", params,
                new Listener<Status>() {
                    @Override
                    public void onResponse(Object mTag, Status response) {
                        if (onSuccess != null)
                            onSuccess.success(response);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (onFailure != null)
                            onFailure.failure(new Reason(error));
                    }
                }, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createGroup(Group group, Listener<Status> listener, ErrorListener errorListener) {
        String params = gson.toJson(group);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "groups/", params, listener, errorListener, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateGroup(Group group, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(group);
        Request<Status> request = null;
        request = new ApiRequest<>(Request.Method.PUT, baseUrl + "groups/" + group.getName(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteGroup(String groupName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUserGroups(String userName, Listener<UserGroup> listener, ErrorListener error) {
        Request<UserGroup> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + userName + "/groups", null, listener, error, UserGroup.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToGroups(String userName, UserGroup groups, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(groups);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/groups", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToGroup(String userName, String groupName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUserFromGroups(String userName, UserGroup groups, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(groups);
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/groups", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUserFromGroup(String userName, String groupName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/groups/" + groupName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void lockoutUser(String userName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "lockouts/" + userName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void unlockUser(String userName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "lockouts/" + userName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getUserRoster(String userName, Listener<Rosters> listener, ErrorListener error) {
        Request<Rosters> request = new ApiRequest<>(Request.Method.GET, baseUrl + "users/" + userName + "/roster", null, listener, error, Rosters.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToRoster(String userName, Roster rosterItemEntity, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(rosterItemEntity);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "users/" + userName + "/roster", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteFromRoster(String userName, String jid, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "users/" + userName + "/roster" + jid, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateRosterEntry(String userName, Roster rosterItemEntity, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(rosterItemEntity);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "users/" + userName + "/roster/" + rosterItemEntity.getJid(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getChatRooms(JSONObject params, Listener<ChatRooms> listener, ErrorListener error) {
        Request<ChatRooms> request = new ApiRequest<>(Request.Method.GET, baseUrl + "chatrooms/", (params == null) ? null : params.toString(), listener, error, ChatRooms.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getChatRoom(String roomName, Listener<ChatRoom> listener, ErrorListener error) {
        Request<ChatRoom> request = new ApiRequest<>(Request.Method.GET, baseUrl + "chatrooms/" + roomName, null, listener, error, ChatRoom.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getChatRooms(Listener<ChatRooms> listener, ErrorListener error) {
        getChatRooms(null, listener, error);
    }

    public void getChatRoomParticipants(String roomName, Listener<Participants> listener, ErrorListener error) {
        String path = "chatrooms/" + roomName + "/participants";
        Request<Participants> request = new ApiRequest<>(Request.Method.GET, baseUrl + path, null, listener, error, Participants.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getChatRoomOccupants(String roomName, Listener<Occupants> listener, ErrorListener error) {
        String path = baseUrl + "chatrooms/" + roomName + "/occupants";
        Request<Occupants> request = new ApiRequest<>(Request.Method.GET, path, null, listener, error, Occupants.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createChatRoom(ChatRoom room, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(room);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "chatrooms/", params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteChatRoom(String chatRoomName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "chatrooms/" + chatRoomName, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateChatRoom(ChatRoom room, Listener<Status> listener, ErrorListener error) {
        String params = null;
        try {
            params = mapper.writeValueAsString(room);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "chatrooms/" + room.getRoomName(), params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addUserToChatRoom(String chatRoomName, String userName, String role, Listener<Status> listener, ErrorListener error) {
        String path = baseUrl + "chatrooms/" + chatRoomName + "/" + role + "/" + userName;
        Request<Status> request = new ApiRequest<>(Request.Method.POST, path, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void addGroupToChatRoom(String chatRoomName, String group, String role, Listener<Status> listener, ErrorListener error) {
        String path = baseUrl + "chatrooms/" + chatRoomName + "/" + role + "/group/" + group;
        Request<Status> request = new ApiRequest<>(Request.Method.POST, path, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteUserFromChatRoom(String chatRoomName, String groupName, String role, Listener<Status> listener, ErrorListener error) {
        String path = baseUrl + "chatrooms/" + chatRoomName + "/" + role + "/" + groupName;
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, path, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteGroupFromChatRoom(String chatRoomName, String groupName, String role, Listener<Status> listener, ErrorListener error) {
        String path = baseUrl + "chatrooms/" + chatRoomName + "/" + role + "/group/" + groupName;
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, path, null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getSessions(Listener<Sessions> listener, ErrorListener error) {
        Request<Sessions> request = new ApiRequest<>(Request.Method.GET, baseUrl + "sessions/",
                null, listener, error, Sessions.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getSessions(String username, Listener<Sessions> listener, ErrorListener error) {
        Request<Sessions> request = new ApiRequest<>(Request.Method.GET, baseUrl + "sessions/" + username,
                null, listener, error, Sessions.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void closeSessions(String username, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "sessions/" + username,
                null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void broadcast(Message message, Listener<Status> listener,
                          ErrorListener error) {
        String params = gson.toJson(message);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "messages/users/", params,
                listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }


    public void getProperties(Listener<SystemProperties> listener, ErrorListener error) {
        Request<SystemProperties> request = new ApiRequest<>(Request.Method.GET, baseUrl + "system/properties/",
                null, listener, error, SystemProperties.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getProperty(String propertyName, Listener<Property> listener, ErrorListener error) {
        Request<Property> request = new ApiRequest<>(Request.Method.GET, baseUrl + "system/properties/" + propertyName,
                null, listener, error, Property.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void createProperty(Property property, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(property);
        Request<Status> request = new ApiRequest<>(Request.Method.POST, baseUrl + "system/properties/",
                params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void deleteProperty(String propertyName, Listener<Status> listener, ErrorListener error) {
        Request<Status> request = new ApiRequest<>(Request.Method.DELETE, baseUrl + "system/properties/" + propertyName,
                null, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void updateProperty(Property property, Listener<Status> listener, ErrorListener error) {
        String params = gson.toJson(property);
        Request<Status> request = new ApiRequest<>(Request.Method.PUT, baseUrl + "system/properties/" + property.getKey(),
                params, listener, error, Status.class, token);
        Volley.newRequestQueue(context).add(request);
    }

    public void getStatistics(Listener<Statistics> listener, ErrorListener error) {
        Request<Statistics> request = new ApiRequest<>(Request.Method.GET, baseUrl + "system/statistics/sessions",
                null, listener, error, Statistics.class, token);
        Volley.newRequestQueue(context).add(request);
    }


}


