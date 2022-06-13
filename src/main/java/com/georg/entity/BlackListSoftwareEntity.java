package com.georg.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@FieldNameConstants
@Getter
@Setter
public class BlackListSoftwareEntity extends PanacheEntity {
    @NotNull
    @Column(nullable = false)
    private String name;
    private String reason;
}
