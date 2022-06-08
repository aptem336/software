package com.georg.resource.report;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/report")
public class ReportResourceWebService implements ReportResource {
    @Inject
    ReportResourceImpl reportResourceImpl;
    @GET
    @Override
    public Uni<List<ReportResponseBody>> get() {
        return reportResourceImpl.get();
    }
}
