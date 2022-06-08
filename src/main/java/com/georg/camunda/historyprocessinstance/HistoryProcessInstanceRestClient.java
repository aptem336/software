package com.georg.camunda.historyprocessinstance;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/history/process-instance")
@RegisterRestClient(configKey = "camunda-rest-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface HistoryProcessInstanceRestClient {
    /**
     * POST /history/process-instance
     * @param camundaHistoryProcessInstanceQueryRequestBody A JSON object
     * @return A JSON array of historic process instance objects
     */
    @POST
    Uni<List<CamundaHistoryProcessInstanceQueryResponseBody>> get(
            CamundaHistoryProcessInstanceQueryRequestBody camundaHistoryProcessInstanceQueryRequestBody);
}
