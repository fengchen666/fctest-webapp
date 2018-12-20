package com.fctest.utility;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class MetaData {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaData.class);

    private JSONObject metaObject = new JSONObject();

    private String lastcommitsha = "";

    public JSONObject getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(JSONObject metaObject) {
        this.metaObject = metaObject;
    }

    public String getLastcommitsha() {
        return lastcommitsha;
    }

    public void setLastcommitsha(String lastcommitsha) {
        this.lastcommitsha = lastcommitsha;
    }

    public MetaData() throws IOException {

        //Get the lastcommitsha
        getLastcommitshaGit();

    }

    private void getLastcommitshaGit() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));

        lastcommitsha = properties.get("git.commit.id.describe-short").toString();

        LOGGER.info("lastcommitsha is " + lastcommitsha);

    }

    public JSONObject metaJsonResponse() {

        //Initialize the metadata
        metaObject.put("lastcommitsha", lastcommitsha);
        metaObject.put("description", "technical test");
        metaObject.put("version", "1.0");

        return metaObject;
    }
}
