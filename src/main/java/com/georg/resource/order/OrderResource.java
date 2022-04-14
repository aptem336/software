package com.georg.resource.order;

import io.smallrye.mutiny.Uni;

public interface OrderResource {
    Uni<StartOrderResponseBody> start(StartOrderRequestBody startOrderResponseBody);
}
