package com.georg.generator;

import io.smallrye.mutiny.Uni;

public interface OrderNumberGenerator {
    Uni<Long> generate();
}
