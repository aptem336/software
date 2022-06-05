package com.georg.enricher;

import java.util.function.Function;

public interface Enricher<T> extends Function<T, T> {
}
