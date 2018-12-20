package com.fctest.utility;

import org.json.simple.JSONObject;

public class HealthCheck {

    public static boolean isHealth = false;

    public boolean isHealth() {
        return isHealth;
    }

    public void setHealth(boolean health) {
        isHealth = health;
    }

    public HealthCheck() {

        isHealth = true;
    }

    public JSONObject healthJsonResponse() {

        JSONObject object = new JSONObject();
        object.put("status", "healthy");
        return object;
    }

    public JSONObject unhealthJsonResponse() {

        JSONObject object = new JSONObject();
        object.put("status", "unhealthy");
        return object;
    }

}