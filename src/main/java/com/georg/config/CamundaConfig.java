package com.georg.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "camunda")
public interface CamundaConfig {
    String processDefinitionKey();

    String workerId();

    String workerMaxTasks();
}
