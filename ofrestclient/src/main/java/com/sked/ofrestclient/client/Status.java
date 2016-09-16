package com.sked.ofrestclient.client;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 25-Jul-16.
 */
public class Status {


    private int code;
    private String message;

    public Status() {
    }

    public Status(int code) {
        this.code = code;
    }

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "code " + code + " message : " + message;
    }
}
