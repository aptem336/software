package com.georg.resource.order;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/order")
public class OrderResourceWebService implements OrderResource {
    @Inject
    OrderResourceImpl orderResourceImp;

    @POST
    @Path("/start")
    @Override
    public Uni<StartOrderResponseBody> start(StartOrderRequestBody startOrderResponseBody) {
        return orderResourceImp.start(startOrderResponseBody);
    }
}
