package com.georg.camunda.externaltask;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class CamundaExternalTaskCompleteResponseBody {
    private String workerId;
    private Map<String, Variable> variables;

    @Data
    @Builder(toBuilder = true)
    public static class Variable {
        private String value;
    }
}
