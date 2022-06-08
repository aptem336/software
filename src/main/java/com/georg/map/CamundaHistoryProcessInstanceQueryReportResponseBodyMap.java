package com.georg.map;

import com.georg.camunda.historyprocessinstance.CamundaHistoryProcessInstanceQueryResponseBody;
import com.georg.resource.report.ReportResponseBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class CamundaHistoryProcessInstanceQueryReportResponseBodyMap implements Function<CamundaHistoryProcessInstanceQueryResponseBody, ReportResponseBody> {
    @Override
    public ReportResponseBody apply(CamundaHistoryProcessInstanceQueryResponseBody camundaHistoryProcessInstanceQueryResponseBody) {
        return ReportResponseBody.builder()
                .software(camundaHistoryProcessInstanceQueryResponseBody.getSoftware())
                .price(camundaHistoryProcessInstanceQueryResponseBody.getPrice())
                .startTime(camundaHistoryProcessInstanceQueryResponseBody.getStartTime())
                .startUserId(camundaHistoryProcessInstanceQueryResponseBody.getStartUserId())
                .build();
    }
}
