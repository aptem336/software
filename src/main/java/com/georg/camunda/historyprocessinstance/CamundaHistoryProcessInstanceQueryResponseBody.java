package com.georg.camunda.historyprocessinstance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CamundaHistoryProcessInstanceQueryResponseBody {
    private String id;
    private String software;
    private String price;
    private String startTime;
    private String startUserId;
}
