package io.dustin.apps.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity {


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 아래 주석된 코드는 새로 생성시 updated_at 컬럼도 생성된 시점의 날짜가 들어간다.
     */
//    @LastModifiedDate
//    @Column(name = "updated_at", nullable = true, updatable = false)
//    private LocalDateTime updatedAt;

    @PreUpdate
    public void onUpdatedAt() {
        this.updatedAt = now();
    }

}
