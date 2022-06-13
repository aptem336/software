package com.georg.resource.blacklist;

import com.georg.entity.BlackListSoftwareEntity;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlackListResourceImpl implements BlackListResource {
    @Override
    public Uni<Void> createEntry(BlackListPutEntryRequestBody blackListPutEntryRequestBody) {
        return BlackListSoftwareEntity.builder()
                .name(blackListPutEntryRequestBody.getName())
                .reason(blackListPutEntryRequestBody.getReason())
                .build()
                .persistAndFlush()
                .replaceWithVoid();
    }
}
