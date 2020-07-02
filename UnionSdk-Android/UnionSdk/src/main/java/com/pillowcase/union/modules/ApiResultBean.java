package com.pillowcase.union.modules;

import org.json.JSONObject;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-02 11:01
 * Description ：
 */
public class ApiResultBean {
    private int Code;
    private String Message;

    public ApiResultBean() {
    }

    @Override
    public String toString() {
        return "ApiResultBean{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                '}';
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String result() {
        JSONObject object = new JSONObject();
        try {
            object.put("Code", getCode());
            object.put("Message", getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object.toString();
    }
}
