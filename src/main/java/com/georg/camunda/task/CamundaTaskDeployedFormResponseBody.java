package com.georg.camunda.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.georg.resource.task.TaskFormResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CamundaTaskDeployedFormResponseBody {
    private List<Component> components;

    @Data
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component {
        private String label;
        private Type type;
        private String key;
        private Validate validate;
        private List<Value> values;

        public enum Type {
            textfield,
            checkbox,
            select,
            number
        }

        @Data
        @Builder(toBuilder = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Validate {
            private Boolean readonly;
            private Boolean required;
        }

        @Data
        @Builder(toBuilder = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Value {
            private String value;
            private String label;
        }
    }
}
