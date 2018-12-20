package com.fctest;

import com.fctest.utility.HealthCheck;
import com.fctest.utility.MetaData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;

/**
 * Root resource (exposed at "ops" path)
 */
@Path("ops")
public class MyOps {

    /**
     * Method handling HTTP GET requests for the root url. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, world!";
    }

    /**
     * Method handling HTTP GET requests for the health url. The returned object will be sent
     * to the client as JSON media type.
     *
     * @return JSON that will be returned as a json response.
     */
    @GET
    @Path("health")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHealth() {

        Response response = null;

        HealthCheck healthCheck = new HealthCheck();

        if (healthCheck.isHealth()){
            response = Response.status(Status.OK).entity(healthCheck.healthJsonResponse().toString()).build();
        }else {
            response = Response.status(Status.OK).entity(healthCheck.unhealthJsonResponse().toString()).build();
        }
        return response;
    }

    /**
     * Method handling HTTP GET requests for the metadata url. The returned object will be sent
     * to the client as JSON media type.
     *
     * @return JSON that will be returned as a json response.
     */
    @GET
    @Path("metadata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMetadata() throws IOException {

        Response response = null;

        MetaData metaData = new MetaData();

        response = Response.status(Status.OK).entity(metaData.metaJsonResponse().toString()).build();

        return response;
    }

}
