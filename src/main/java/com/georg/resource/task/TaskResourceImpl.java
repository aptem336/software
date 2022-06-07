package com.georg.resource.task;

import com.georg.camunda.task.CamundaTaskCompleteRequestBody;
import com.georg.camunda.task.TaskRestClient;
import com.georg.enricher.TaskQueryResponseBodyOrderNumberEnricher;
import com.georg.map.CamundaTaskDeployedFormTaskFormResponseBodyMap;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .transformToUni(taskQueryRequestBodyUnis -> taskQueryRequestBodyUnis.isEmpty()
                        ? Uni.createFrom().item(Collections.emptyList())
                        : Uni.join().all(taskQueryRequestBodyUnis).andCollectFailures());
    }

    @Override
    public Uni<TaskFormResponseBody> getForm(String taskId) {
        return taskRestClient.getDeployedForm(taskId)
                .onItem()
                .transform(camundaTaskDeployedFormTaskFormResponseBodyMap);
    }

    @Override
    public Uni<Map<String, String>> getVariables(String taskId) {
        return taskRestClient.getVariables(taskId)
                .onItem()
                .transform(stringCamundaTaskVariablesResponseBodyMap ->
                        stringCamundaTaskVariablesResponseBodyMap.entrySet()
                                .stream()
                                .collect(HashMap::new, (map, entry) ->
                                                map.put(entry.getKey(), entry.getValue().getValue()),
                                        HashMap::putAll));
    }

    @Override
    public Uni<Void> complete(String taskId, Map<String, String> variables) {
        return taskRestClient.complete(taskId, CamundaTaskCompleteRequestBody.builder()
                .variables(variables.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                taskVariablesRequestBodyEntry ->
                                        CamundaTaskCompleteRequestBody.Variable.builder()
                                                .value(taskVariablesRequestBodyEntry.getValue())
                                                .build())))
                .build());
    }
}
