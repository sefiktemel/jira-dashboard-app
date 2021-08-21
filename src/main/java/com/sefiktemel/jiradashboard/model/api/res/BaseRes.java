package com.sefiktemel.jiradashboard.model.api.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseRes {
    public static final String MSG_UNKNOWN = "unknown";
    public static final String MSG_OK = "ok";
    public static final String MSG_NOT_FOUND = "record not found";
    public static final String MSG_UNAUTHORIZED = "unauthorized";
    public static final String MSG_SESSION_TIMEOUT = "session timeout";
    public static final String MSG_ALREADY_EXIST = "already exist";
    public static final String MSG_NOT_ALLOWED = "not allowed";
    public static final int CODE_UNKNOWN = 100;
    public static final int CODE_OK = 200;
    public static final int CODE_NOT_FOUND = 201;
    public static final int CODE_UNAUTHORIZED = 202;
    public static final int CODE_SESSION_TIMEOUT = 203;
    public static final int CODE_ALREADY_EXIST = 204;
    public static final int CODE_NOT_ALLOWED = 206;

    private int code;
    private String message;

    public BaseRes() {
        code = CODE_OK;
        message = MSG_OK;
    }

    public void setBaseRes(BaseRes baseRes) {
        this.code = baseRes.code;
        this.message = baseRes.message;
    }

    @JsonIgnore
    public boolean isSessionOK() {
        return this.code == CODE_OK;
    }
}