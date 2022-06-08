package com.georg.resource.report;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ReportResource {
    Uni<List<ReportResponseBody>> get();
}
