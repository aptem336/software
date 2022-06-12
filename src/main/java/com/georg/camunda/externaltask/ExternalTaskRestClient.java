package com.georg.camunda.externaltask;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * <a href="https://docs.camunda.org/manual/7.5/reference/rest/external-task/">https://docs.camunda.org/manual/7.5/reference/rest/external-task/</a>
 */
@Path("/external-task")
@RegisterRestClient(configKey = "camunda-rest-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ExternalTaskRestClient {
    /**
     * POST /external-task/fetchAndLock
     *
     * @param camundaExternalTaskFetchAndLockRequestBody A JSON object
     * @return A JSON array of external task objects
     */
    @POST
    Uni<List<CamundaExternalTaskFetchAndLockResponseBody>> fetchAndLock(CamundaExternalTaskFetchAndLockRequestBody camundaExternalTaskFetchAndLockRequestBody);

    /**
     * POST /external-task/{id}/complete
     *
     * @param camundaExternalTaskCompleteResponseBody A JSON object
     * @return This method returns no content.
     */
    @POST
    @Path("/{id}/complete")
    Uni<Void> complete(@PathParam("id") String id, CamundaExternalTaskCompleteResponseBody camundaExternalTaskCompleteResponseBody);
}
