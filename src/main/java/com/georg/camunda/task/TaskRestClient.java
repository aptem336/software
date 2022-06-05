package com.georg.camunda.task;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
     * @param camundaTaskQueryRequestBody A JSON object
     * @return A JSON array of task objects
     */
    @POST
    Uni<List<CamundaTaskQueryResponseBody>> get(CamundaTaskQueryRequestBody camundaTaskQueryRequestBody);
}
