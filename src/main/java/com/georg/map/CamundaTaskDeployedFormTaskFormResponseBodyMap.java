package com.georg.map;

import com.georg.camunda.task.CamundaTaskDeployedFormResponseBody;
import com.georg.resource.task.TaskFormResponseBody;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class CamundaTaskDeployedFormTaskFormResponseBodyMap implements Function<CamundaTaskDeployedFormResponseBody, TaskFormResponseBody> {
    @Inject
    ComponentFieldMap componentFieldMap;

    @Override
    public TaskFormResponseBody apply(CamundaTaskDeployedFormResponseBody camundaTaskDeployedFormResponseBody) {
        return TaskFormResponseBody.builder()
                .fields(camundaTaskDeployedFormResponseBody.getComponents()
                        .stream()
                        .map(componentFieldMap)
                        .collect(Collectors.toList()))
                .build();
    }

    @ApplicationScoped
    private static class ComponentFieldMap implements Function<CamundaTaskDeployedFormResponseBody.Component, TaskFormResponseBody.Field> {
        @Override
        public TaskFormResponseBody.Field apply(CamundaTaskDeployedFormResponseBody.Component component) {
            return TaskFormResponseBody.Field.builder()
                    .label(component.getLabel())
                    .type(TaskFormResponseBody.Field.Type.valueOf(component.getType().name()))
                    .key(component.getKey())
                    .readonly(Optional.ofNullable(component.getValidate())
                            .map(CamundaTaskDeployedFormResponseBody.Component.Validate::getReadonly)
                            .orElse(false))
                    .required(Optional.ofNullable(component.getValidate())
                            .map(CamundaTaskDeployedFormResponseBody.Component.Validate::getRequired)
                            .orElse(false))
                    .build();
        }
    }
}
