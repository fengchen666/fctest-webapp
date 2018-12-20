package com.fctest;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyResourceTest extends JerseyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyResourceTest.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(MyOps.class);
    }

    /**
     * Test to see that the message "Hello, world!" is sent in the response for the root url.
     */
    @Test
    public void testGetRoot() {
        final String responseMsg = target().path("ops").request().get(String.class);

        assertEquals("Hello, world!", responseMsg);
    }

    /**
     * Test to see that the status is "healthy" in the response for the health url.
     */
    @Test
    public void testGetHealth() throws ParseException {
        final String responseMsg = target().path("ops/health").request().get(String.class);

        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(responseMsg);

        LOGGER.info("responseHealthJSON = " + responseJSON);
        assertEquals("healthy", responseJSON.get("status"));

    }

    /**
     * Test to see that the version is 1.0 in the response for the meta url.
     */
    @Test
    public void testMeta() throws ParseException {

        final String responseMsg = target().path("ops/metadata").request().get(String.class);

        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(responseMsg);

        LOGGER.info("responseMetadataJSON = " + responseJSON);
        assertEquals("1.0", responseJSON.get("version"));
    }
}