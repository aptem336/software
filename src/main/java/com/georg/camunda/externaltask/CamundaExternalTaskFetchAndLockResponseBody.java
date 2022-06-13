package com.georg.camunda.externaltask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CamundaExternalTaskFetchAndLockResponseBody {
    private String id;
    private String topicName;
    private Map<String, Variable> variables;

    @Data
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Variable {
        private String value;
    }
}
