package com.georg.enricher;

import com.georg.camunda.historyprocessinstance.CamundaHistoryProcessInstanceQueryResponseBody;
import com.georg.camunda.historyvariableinstance.CamundaHistoryVariableInstanceQueryRequestBody;
import com.georg.camunda.historyvariableinstance.HistoryProcessVariableRestClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamundaHistoryProcessInstanceQueryResponseBodyPriceEnricher implements Enricher<Uni<CamundaHistoryProcessInstanceQueryResponseBody>> {
    @RestClient
    HistoryProcessVariableRestClient historyProcessVariableRestClient;
    @Override
    public Uni<CamundaHistoryProcessInstanceQueryResponseBody> apply(Uni<CamundaHistoryProcessInstanceQueryResponseBody> camundaHistoryProcessInstanceQueryResponseBodyUni) {
        return camundaHistoryProcessInstanceQueryResponseBodyUni
                .onItem()
                .transformToUni(camundaHistoryProcessInstanceQueryResponseBody ->
                        historyProcessVariableRestClient.get(CamundaHistoryVariableInstanceQueryRequestBody.builder()
                                        .variableName("price")
                                        .processInstanceId(camundaHistoryProcessInstanceQueryResponseBody.getId())
                                        .build())
                                .onItem()
                                .transform(camundaHistoryVariableInstanceQueryResponseBodies ->
                                        camundaHistoryProcessInstanceQueryResponseBody.toBuilder()
                                                .price(camundaHistoryVariableInstanceQueryResponseBodies.get(0).getValue())
                                                .build())
                                .onFailure()
                                .recoverWithItem(() -> camundaHistoryProcessInstanceQueryResponseBody));
    }
}
