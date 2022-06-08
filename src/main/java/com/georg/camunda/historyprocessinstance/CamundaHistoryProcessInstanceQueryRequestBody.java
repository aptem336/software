package com.georg.camunda.historyprocessinstance;

import com.georg.camunda.task.CamundaTaskQueryRequestBody;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class CamundaHistoryProcessInstanceQueryRequestBody {
    private List<Variable> variables;

    @Data
    @Builder
    public static class Variable {
        private String name;
        private String value;
        private CamundaTaskQueryRequestBody.ProcessVariable.Operator operator;

        public enum Operator {
            like
        }
    }
}
