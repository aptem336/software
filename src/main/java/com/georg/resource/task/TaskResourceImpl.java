package com.georg.resource.task;

import com.georg.camunda.task.TaskRestClient;
import com.georg.enricher.TaskQueryResponseBodyOrderNumberEnricher;
import com.georg.map.CamundaTaskDeployedFormTaskFormResponseBodyMap;
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

    @Inject
    TaskQueryResponseBodyOrderNumberEnricher taskQueryResponseBodyOrderNumberEnricher;

    @Inject
    CamundaTaskDeployedFormTaskFormResponseBodyMap camundaTaskDeployedFormTaskFormResponseBodyMap;

    @Override
    public Uni<List<TaskQueryResponseBody>> get(TaskQueryRequestBody taskQueryRequestBody) {
        return taskRestClient.get(taskQueryCamundaTaskQueryRequestBodyMap.apply(taskQueryRequestBody))
                .onItem()
                .transform(camundaTaskQueryResponseBodies -> camundaTaskQueryResponseBodies
                        .stream()
                        .map(camundaTaskQueryResponseBody -> Uni.createFrom()
                                .item(camundaTaskQueryTaskQueryResponseBodyMap.apply(camundaTaskQueryResponseBody))
                                .plug(taskQueryResponseBodyOrderNumberEnricher))
                        .collect(Collectors.toList()))
                .onItem()
                .transformToUni(taskQueryRequestBodyUnis -> Uni.join().all(taskQueryRequestBodyUnis).andFailFast());
    }

    @Override
    public Uni<TaskFormResponseBody> getForm(String taskId) {
        return taskRestClient.getDeployedForm(taskId)
                .onItem()
                .transform(camundaTaskDeployedFormTaskFormResponseBodyMap);
    }
}
