package com.georg.externaltaskexecutor;

import com.georg.camunda.externaltask.CamundaExternalTaskCompleteResponseBody;
import com.georg.camunda.externaltask.CamundaExternalTaskFetchAndLockResponseBody;
import com.georg.config.CamundaConfig;
import com.georg.entity.BlackListSoftwareEntity;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Optional;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/checkStopFactors")
public class CheckStopFactorsExternalTaskExecutor implements ExternalTaskExecutor {
    @Inject
    CamundaConfig camundaConfig;

    @Override
    public Uni<CamundaExternalTaskCompleteResponseBody> execute(CamundaExternalTaskFetchAndLockResponseBody camundaExternalTaskFetchAndLockResponseBody) {
        return BlackListSoftwareEntity
                .<BlackListSoftwareEntity>find(BlackListSoftwareEntity.Fields.name,
                        camundaExternalTaskFetchAndLockResponseBody.getVariables().get("software").getValue())
                .firstResult()
                .onItem()
                .transform(blackListSoftwareEntity -> CamundaExternalTaskCompleteResponseBody.builder()
                        .workerId(camundaConfig.workerId())
                        .variables(Map.of("cancellationReason",
                                CamundaExternalTaskCompleteResponseBody.Variable.builder()
                                        .value(Optional.ofNullable(blackListSoftwareEntity)
                                                .map(BlackListSoftwareEntity::getReason)
                                                .orElse(null))
                                        .build()))
                        .build());
    }
}
