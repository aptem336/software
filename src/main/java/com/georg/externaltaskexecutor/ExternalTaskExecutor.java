package com.georg.externaltaskexecutor;

import com.georg.camunda.externaltask.CamundaExternalTaskCompleteResponseBody;
import com.georg.camunda.externaltask.CamundaExternalTaskFetchAndLockResponseBody;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.POST;
public interface ExternalTaskExecutor {
    @POST
    Uni<CamundaExternalTaskCompleteResponseBody> execute(CamundaExternalTaskFetchAndLockResponseBody camundaExternalTaskFetchAndLockResponseBody);
}
