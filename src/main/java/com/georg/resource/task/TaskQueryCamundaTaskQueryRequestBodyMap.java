package com.georg.resource.task;

import com.georg.camunda.task.CamundaTaskQueryRequestBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class TaskQueryCamundaTaskQueryRequestBodyMap implements Function<TaskQueryRequestBody, CamundaTaskQueryRequestBody> {
    @Override
    public CamundaTaskQueryRequestBody apply(TaskQueryRequestBody taskQueryRequestBody) {
        return CamundaTaskQueryRequestBody.builder()
                .processInstanceId(taskQueryRequestBody.getProcessInstanceId())
                .build();
    }
}
