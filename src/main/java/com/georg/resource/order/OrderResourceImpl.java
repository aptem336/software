package com.georg.resource.order;

import com.georg.camunda.processdefinition.ProcessDefinitionRestClient;
import com.georg.config.CamundaConfig;
import com.georg.map.StartOrderStartProcessInstanceRequestBodyMap;
import com.georg.map.StartProcessInstanceStartOrderResponseBodyMap;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderResourceImpl implements OrderResource {
    @Inject
    CamundaConfig camundaConfig;
    @Inject
    StartOrderStartProcessInstanceRequestBodyMap startOrderStartProcessInstanceRequestBodyMap;
    @RestClient
    ProcessDefinitionRestClient processDefinitionRestClient;
    @Inject
    StartProcessInstanceStartOrderResponseBodyMap startProcessInstanceStartOrderResponseBodyMap;

    @Override
    public Uni<StartOrderResponseBody> start(StartOrderRequestBody startOrderResponseBody) {
        return processDefinitionRestClient.startProcessInstanceByKey(camundaConfig.processDefinitionKey(),
                        startOrderStartProcessInstanceRequestBodyMap.apply(startOrderResponseBody))
                .onItem()
                .transform(startProcessInstanceStartOrderResponseBodyMap);
    }
}
