package com.georg.resource.blacklist;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/black-list")
public class BlackListResourceWebService implements BlackListResource {
    @Inject
    BlackListResourceImpl blackListResourceImpl;

    @POST
    @Override
    public Uni<Void> createEntry(BlackListPutEntryRequestBody blackListPutEntryRequestBody) {
        return blackListResourceImpl.createEntry(blackListPutEntryRequestBody);
    }
}
