package com.sked.ofrestclient.client;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.sked.ofrestclient.entity.AuthenticationMode;
import com.sked.ofrestclient.entity.AuthenticationToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * XMPP Admin, All rights Reserved
 * Created by Sanjeet on 24-Jul-16.
 */
public class ApiRequest<T> extends JsonRequest<T> {

    private Class<T> clazz;
    private AuthenticationToken token;
    private int method;

    public ApiRequest(int method, String url, String jsonRequest, Response.Listener<T> listener,
                      Response.ErrorListener errorListener, Class<T> clazz, AuthenticationToken token) {
        super(method, url, jsonRequest, listener, errorListener);
        this.clazz = clazz;
        this.token = token;
        this.method = method;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        if (token.getAuthMode() == AuthenticationMode.SHARED_SECRET_KEY) {
            headers.put("Authorization", token.getSharedSecretKey());
        } else if (token.getAuthMode() == AuthenticationMode.BASIC_AUTH) {
            String base64 = Base64.encodeToString((token.getUsername() + ":" + token.getPassword()).getBytes(), Base64.DEFAULT);
            headers.put("Authorization", "Basic " + base64);
        }
        headers.put("Accept", "application/json");
        return headers;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString;
            if (method == Method.GET) {
                jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            } else {
                JSONObject responseJson = new JSONObject();
                responseJson.put("code", response.statusCode);
                responseJson.put("message", response.headers.toString());
                jsonString = responseJson.toString();
            }
            Gson g = new Gson();
            return Response.success(g.fromJson(jsonString, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}
