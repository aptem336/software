package com.georg.resource.blacklist;

import io.smallrye.mutiny.Uni;

public interface BlackListResource {
    Uni<Void> createEntry(BlackListPutEntryRequestBody blackListPutEntryRequestBody);
}
