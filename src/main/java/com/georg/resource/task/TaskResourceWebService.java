package com.georg.resource.task;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/task")
public class TaskResourceWebService implements TaskResource {
    @Inject
    TaskResourceImpl taskResourceImpl;
    @POST
    @Override
    public Uni<List<TaskQueryResponseBody>> get(TaskQueryRequestBody taskQueryRequestBody) {
        return taskResourceImpl.get(taskQueryRequestBody);
    }
}
