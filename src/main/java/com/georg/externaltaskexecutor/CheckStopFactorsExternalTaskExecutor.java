package com.georg.externaltaskexecutor;

import com.georg.camunda.externaltask.CamundaExternalTaskCompleteResponseBody;
import com.georg.camunda.externaltask.CamundaExternalTaskFetchAndLockResponseBody;
import com.georg.config.CamundaConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Named("checkStopFactorsExternalTaskExecutor")
public class CheckStopFactorsExternalTaskExecutor implements ExternalTaskExecutor {
    @Inject
    CamundaConfig camundaConfig;

    @Override
    public CamundaExternalTaskCompleteResponseBody execute(CamundaExternalTaskFetchAndLockResponseBody camundaExternalTaskFetchAndLockResponseBody) {
        return CamundaExternalTaskCompleteResponseBody.builder()
                .workerId(camundaConfig.workerId())
                .variables(Map.of("cancellationReason", CamundaExternalTaskCompleteResponseBody.Variable.builder()
                        .value(null)
                        .build()))
                .build();
    }
}
