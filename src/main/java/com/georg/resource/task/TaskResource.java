package com.georg.resource.task;

import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.Map;

public interface TaskResource {
    Uni<List<TaskQueryResponseBody>> get(TaskQueryRequestBody taskQueryRequestBody);

    Uni<TaskFormResponseBody> getForm(String taskId);

    Uni<Map<String, String>> getVariables(String taskId);
}
