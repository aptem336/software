package com.georg.map;

import com.georg.camunda.processdefinition.StartProcessInstanceResponseBody;
import com.georg.resource.order.StartOrderResponseBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class StartProcessInstanceStartOrderResponseBodyMap implements Function<StartProcessInstanceResponseBody, StartOrderResponseBody>{
    @Override
    public StartOrderResponseBody apply(StartProcessInstanceResponseBody startProcessInstanceResponseBody) {
        return StartOrderResponseBody.builder()
                .processInstanceId(startProcessInstanceResponseBody.getId())
                .build();
    }
}
