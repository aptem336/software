package com.georg.generator;

import com.georg.OrderNumberEntity;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderNumberGeneratorImpl implements OrderNumberGenerator {
    @Override
    public Uni<Long> generate() {
        return new OrderNumberEntity().<OrderNumberEntity>persistAndFlush()
                .onItem()
                .transform(OrderNumberEntity::getOrderNumber);
    }
}
