package com.georg.camunda.task;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class CamundaTaskQueryRequestBody {
    private String processInstanceId;
    private List<ProcessVariable> processVariables;

    @Data
    @Builder
    public static class ProcessVariable {
        private String name;
        private String value;
        private Operator operator;

        public enum Operator {
            like
        }
    }
}
