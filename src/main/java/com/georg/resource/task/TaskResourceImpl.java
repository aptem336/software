package com.georg.resource.task;

import com.georg.camunda.task.TaskRestClient;
import com.georg.enricher.TaskQueryResponseBodyOrderNumberEnricher;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskResourceImpl implements TaskResource {
    @Inject
    TaskQueryCamundaTaskQueryRequestBodyMap taskQueryCamundaTaskQueryRequestBodyMap;
    @RestClient
    TaskRestClient taskRestClient;

    @Inject
    CamundaTaskQueryTaskQueryResponseBodyMap camundaTaskQueryTaskQueryResponseBodyMap;

    @Override
    public Uni<List<TaskQueryResponseBody>> get(TaskQueryRequestBody taskQueryRequestBody) {
        return taskRestClient.get(taskQueryCamundaTaskQueryRequestBodyMap.apply(taskQueryRequestBody))
                .onItem()
                .transform(camundaTaskQueryResponseBodies -> camundaTaskQueryResponseBodies
                        .stream()
                        .map(camundaTaskQueryTaskQueryResponseBodyMap)
                        .collect(Collectors.toList()));
    }
}
