package com.georg.camunda.processdefinition;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(toBuilder = true)
public class StartProcessInstanceRequestBody {
    private Map<String, Variable> variables;

    @Data
    @Builder(toBuilder = true)
    public static class Variable {
        private String value;
    }
}
