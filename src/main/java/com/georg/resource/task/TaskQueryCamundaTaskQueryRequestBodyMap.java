package com.georg.resource.task;

import com.georg.camunda.task.CamundaTaskQueryRequestBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@ApplicationScoped
public class TaskQueryCamundaTaskQueryRequestBodyMap implements Function<TaskQueryRequestBody, CamundaTaskQueryRequestBody> {
    @Override
    public CamundaTaskQueryRequestBody apply(TaskQueryRequestBody taskQueryRequestBody) {
        return CamundaTaskQueryRequestBody.builder()
                .processInstanceId(taskQueryRequestBody.getProcessInstanceId())
                .processVariables(Optional.ofNullable(taskQueryRequestBody.getOrderNumber())
                        .filter(orderNumber -> !orderNumber.isEmpty())
                        .map(orderNumber -> List.of(CamundaTaskQueryRequestBody.ProcessVariable.builder()
                                .name("orderNumber")
                                .value(orderNumber)
                                .operator(CamundaTaskQueryRequestBody.ProcessVariable.Operator.like)
                                .build()))
                        .orElse(null))
                .build();
    }
}
