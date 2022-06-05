package com.georg.enricher;

import com.georg.camunda.processdefinition.StartProcessInstanceRequestBody;
import com.georg.generator.OrderNumberGenerator;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class StartProcessInstanceOrderNumberEnricher implements Enricher<Uni<StartProcessInstanceRequestBody>> {
    @Inject
    OrderNumberGenerator orderNumberGenerator;

    @Override
    public Uni<StartProcessInstanceRequestBody> apply(Uni<StartProcessInstanceRequestBody> startProcessInstanceRequestBodyUni) {
        return Uni.combine().all().unis(
                        orderNumberGenerator.generate(),
                        startProcessInstanceRequestBodyUni)
                .combinedWith((orderNumber, startProcessInstanceRequestBody) ->
                        startProcessInstanceRequestBody
                                .toBuilder()
                                .variables(Map.of("orderNumber", StartProcessInstanceRequestBody.Variable.builder()
                                        .value(String.valueOf(orderNumber))
                                        .build()))
                                .build());
    }
}
