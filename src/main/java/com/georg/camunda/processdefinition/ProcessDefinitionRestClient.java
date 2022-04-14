package com.georg.camunda.processdefinition;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <a href="https://docs.camunda.org/manual/7.5/reference/rest/process-instance/">https://docs.camunda.org/manual/7.5/reference/rest/process-instance/</a>
 */
@Path("/process-definition")
@RegisterRestClient(configKey = "camunda-rest-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProcessDefinitionRestClient {
    /**
     * POST /process-definition/{id}/start
     *
     * @param processDefinitionId The id of the process definition to be retrieved.
     * @return created process instance info
     */
    @POST
    @Path("/{processDefinitionId}/start")
    Uni<StartProcessInstanceResponseBody> startProcessInstance(@PathParam("processDefinitionId") String processDefinitionId,
                                                               StartProcessInstanceRequestBody startProcessInstanceRequestBody);
}
