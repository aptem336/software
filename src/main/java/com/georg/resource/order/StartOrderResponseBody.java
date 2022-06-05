package com.georg.resource.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StartOrderResponseBody {
    private String processInstanceId;
}
