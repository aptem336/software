package com.georg.camunda.task;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://docs.camunda.org/manual/7.5/reference/rest/task/">https://docs.camunda.org/manual/7.5/reference/rest/task/</a>
 */
@Path("/task")
@RegisterRestClient(configKey = "camunda-rest-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TaskRestClient {
    /**
     * POST /task
     *
     * @param camundaTaskQueryRequestBody A JSON object
     * @return A JSON array of task objects
     */
    @POST
    Uni<List<CamundaTaskQueryResponseBody>> get(CamundaTaskQueryRequestBody camundaTaskQueryRequestBody);

    /**
     * GET /task/{id}/variables/{varName}
     *
     * @param id      The id of the task to retrieve the variable from.
     * @param varName The name of the variable to get.
     * @return A JSON object
     */
    @GET
    @Path("/{id}/variables/{varName}")
    Uni<CamundaTaskVariableResponseBody> getVariable(@PathParam("id") String id,
                                                     @PathParam("varName") String varName);


    /**
     * GET /task/{id}/deployed-form
     *
     * @param id The id of the task to get the deployed form for.
     * @return An object with the deployed form content.
     */
    @GET
    @Path("/{id}/deployed-form")
    Uni<CamundaTaskDeployedFormResponseBody> getDeployedForm(@PathParam("id") String id);

    /**
     * GET /task/{id}/variables
     *
     * @param taskId The id of the task to retrieve the variables from.
     * @return A JSON object of variables key-value pairs. Each key is a variable name and each value a variable value object that has the following properties:
     */
    @GET
    @Path("/{taskId}/form-variables")
    Uni<Map<String, CamundaTaskVariablesResponseBody>> getVariables(@PathParam("taskId") String taskId);
}
