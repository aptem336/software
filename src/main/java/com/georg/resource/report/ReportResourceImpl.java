package com.georg.resource.report;

import com.georg.camunda.historyprocessinstance.CamundaHistoryProcessInstanceQueryRequestBody;
import com.georg.camunda.historyprocessinstance.HistoryProcessInstanceRestClient;
import com.georg.enricher.CamundaHistoryProcessInstanceQueryResponseBodyPriceEnricher;
import com.georg.enricher.CamundaHistoryProcessInstanceQueryResponseBodySoftwareEnricher;
import com.georg.map.CamundaHistoryProcessInstanceQueryReportResponseBodyMap;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReportResourceImpl implements ReportResource {
    @RestClient
    HistoryProcessInstanceRestClient historyProcessInstanceRestClient;
    @Inject
    CamundaHistoryProcessInstanceQueryReportResponseBodyMap camundaHistoryProcessInstanceQueryReportResponseBodyMap;
    @Inject
    CamundaHistoryProcessInstanceQueryResponseBodySoftwareEnricher camundaHistoryProcessInstanceQueryResponseBodySoftwareEnricher;
    @Inject
    CamundaHistoryProcessInstanceQueryResponseBodyPriceEnricher camundaHistoryProcessInstanceQueryResponseBodyPriceEnricher;

    @Override
    public Uni<List<ReportResponseBody>> get() {
        return historyProcessInstanceRestClient.get(CamundaHistoryProcessInstanceQueryRequestBody.builder()
                        .build())
                .onItem()
                .transform(camundaHistoryProcessInstanceQueryResponseBodies -> camundaHistoryProcessInstanceQueryResponseBodies
                        .stream()
                        .map(camundaHistoryProcessInstanceQueryResponseBody -> Uni.createFrom()
                                .item(camundaHistoryProcessInstanceQueryResponseBody)
                                .plug(camundaHistoryProcessInstanceQueryResponseBodySoftwareEnricher)
                                .plug(camundaHistoryProcessInstanceQueryResponseBodyPriceEnricher)
                                .onItem()
                                .transform(camundaHistoryProcessInstanceQueryReportResponseBodyMap))
                        .collect(Collectors.toList()))
                .onItem()
                .transformToUni(ReportResponseBodyUni -> ReportResponseBodyUni.isEmpty()
                        ? Uni.createFrom().item(Collections.emptyList())
                        : Uni.join().all(ReportResponseBodyUni).andCollectFailures());
    }
}
