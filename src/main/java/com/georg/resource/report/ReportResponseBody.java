package com.georg.resource.report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReportResponseBody {
    private String software;
    private String price;
    private String startTime;
    private String startUserId;
}
