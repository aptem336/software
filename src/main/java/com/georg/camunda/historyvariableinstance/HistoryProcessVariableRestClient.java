package com.georg.camunda.historyvariableinstance;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/history/variable-instance")
@RegisterRestClient(configKey = "camunda-rest-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface HistoryProcessVariableRestClient {
    /**
     * POST /history/variable-instance
     * @param camundaHistoryVariableInstanceQueryRequestBody A JSON object
     * @return A JSON object with the following properties:
     */
    @POST
    Uni<List<CamundaHistoryVariableInstanceQueryResponseBody>> get(
            CamundaHistoryVariableInstanceQueryRequestBody camundaHistoryVariableInstanceQueryRequestBody);
}
