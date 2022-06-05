package com.georg.resource.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TaskQueryResponseBody {
    private String id;
    private String name;
    private String assignee;
    private String created;
}
