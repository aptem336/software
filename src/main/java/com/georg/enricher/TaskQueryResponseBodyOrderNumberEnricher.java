package com.georg.enricher;

import com.georg.camunda.task.TaskRestClient;
import com.georg.resource.task.TaskQueryResponseBody;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskQueryResponseBodyOrderNumberEnricher implements Enricher<Uni<TaskQueryResponseBody>> {
    @RestClient
    TaskRestClient taskRestClient;

    @Override
    public Uni<TaskQueryResponseBody> apply(Uni<TaskQueryResponseBody> taskQueryResponseBodyUni) {
        return taskQueryResponseBodyUni
                .onItem()
                .transformToUni(taskQueryResponseBody ->
                        taskRestClient.getVariable(taskQueryResponseBody.getId(), "orderNumber")
                                .onItem()
                                .transform(camundaTaskVariableResponseBody ->
                                        taskQueryResponseBody.toBuilder()
                                                .orderNumber(camundaTaskVariableResponseBody.getValue())
                                                .build())
                                .onFailure()
                                .recoverWithItem(() -> taskQueryResponseBody));
    }
}
