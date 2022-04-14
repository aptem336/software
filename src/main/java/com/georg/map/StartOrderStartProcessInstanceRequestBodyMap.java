package com.georg.map;

import com.georg.camunda.processdefinition.StartProcessInstanceRequestBody;
import com.georg.resource.order.StartOrderRequestBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class StartOrderStartProcessInstanceRequestBodyMap implements Function<StartOrderRequestBody, StartProcessInstanceRequestBody> {
    @Override
    public StartProcessInstanceRequestBody apply(StartOrderRequestBody startOrderRequestBody) {
        return StartProcessInstanceRequestBody.builder().build();
    }
}
