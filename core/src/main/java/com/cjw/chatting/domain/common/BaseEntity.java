package com.cjw.chatting.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "CREATE_ID")
    private String createId;

    @Column(name = "UPDATE_ID")
    private String updateId;
}
