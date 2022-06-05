package com.georg.resource.task;

import com.georg.camunda.task.CamundaTaskQueryResponseBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class CamundaTaskQueryTaskQueryResponseBodyMap implements Function<CamundaTaskQueryResponseBody, TaskQueryResponseBody> {
    @Override
    public TaskQueryResponseBody apply(CamundaTaskQueryResponseBody camundaTaskQueryResponseBody) {
        return TaskQueryResponseBody.builder()
                .id(camundaTaskQueryResponseBody.getId())
                .name(camundaTaskQueryResponseBody.getName())
                .assignee(camundaTaskQueryResponseBody.getAssignee())
                .created(camundaTaskQueryResponseBody.getCreated())
                .build();
    }
}
