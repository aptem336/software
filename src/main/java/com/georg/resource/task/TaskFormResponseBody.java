package com.georg.resource.task;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class TaskFormResponseBody {
    private List<Field> fields;

    @Data
    @Builder(toBuilder = true)
    public static class Field {
        private String label;
        private Type type;
        private String key;
        private Boolean readonly;
        private Boolean required;
        private List<Value> values;

        public enum Type {
            textfield,
            checkbox,
            select,
            number
        }

        @Data
        @Builder(toBuilder = true)
        public static class Value {
            private String value;
            private String label;
        }
    }
}
