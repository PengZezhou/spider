package com.yssj.core.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

public class RobotNotify {

    public static void robotMsg(String msg){
        JSONObject json = new JSONObject();
        JSONObject text = new JSONObject();
        JSONObject at = new JSONObject();
        at.append("atMobiles","13911058705");
        at.append("atMobiles","15715716421");
        text.set("content","【创文通知】"+msg);
        json.set("msgtype","text");
        json.set("text",text);
        json.set("at",at);
        String result = HttpUtil.createPost("https://oapi.dingtalk.com/robot/send?access_token=9f718368feafe5c83866f1c98184c400c55027db90e6362e91f1bfd7c529eca2")
                .body(json.toString())
                .header("Content-Type","application/json ;charset=utf-8")
                .execute()
                .body();
    }

}
