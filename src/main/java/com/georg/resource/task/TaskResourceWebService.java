package com.georg.resource.task;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

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

    @GET
    @Path("{taskId}/form")
    @Override
    public Uni<TaskFormResponseBody> getForm(@PathParam("taskId") String taskId) {
        return taskResourceImpl.getForm(taskId);
    }

    @GET
    @Path("{taskId}/variables")
    @Override
    public Uni<Map<String, String>> getVariables(String taskId) {
        return taskResourceImpl.getVariables(taskId);
    }

    @POST
    @Path("{taskId}/complete")
    @Override
    public Uni<Void> complete(@PathParam("taskId") String taskId, Map<String, String> variables) {
        return taskResourceImpl.complete(taskId, variables);
    }
}
