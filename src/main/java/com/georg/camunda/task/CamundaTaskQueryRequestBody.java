package com.georg.camunda.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CamundaTaskQueryRequestBody {
    private String processInstanceId;
}
