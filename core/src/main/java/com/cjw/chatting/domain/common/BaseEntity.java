package com.cjw.chatting.domain.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "CREATE_DATE")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "CREATE_ID")
    @CreatedBy
    private String createId;

    @Column(name = "UPDATE_ID")
    @LastModifiedBy
    private String updateId;
}
