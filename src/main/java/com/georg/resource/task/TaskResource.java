package com.georg.resource.task;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface TaskResource {
    Uni<List<TaskQueryResponseBody>> get(TaskQueryRequestBody taskQueryRequestBody);
}
