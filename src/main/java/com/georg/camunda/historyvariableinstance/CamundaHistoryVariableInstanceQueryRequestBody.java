package com.georg.camunda.historyvariableinstance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CamundaHistoryVariableInstanceQueryRequestBody {
    private String variableName;
    private String processInstanceId;
}
