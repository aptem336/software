package com.georg.camunda.task;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class CamundaTaskCompleteRequestBody {
    private Map<String, Variable> variables;

    @Data
    @Builder(toBuilder = true)
    public static class Variable {
        private String value;
    }
}
