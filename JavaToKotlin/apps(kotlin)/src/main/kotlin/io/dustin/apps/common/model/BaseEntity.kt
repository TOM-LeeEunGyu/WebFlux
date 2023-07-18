package io.dustin.apps.common.model

import jakarta.persistence.Column

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = true)
    private val createdAt: LocalDateTime? = null

    @Column(name = "updated_at")
    private var updatedAt: LocalDateTime? = null

    /**
     * 아래 주석된 코드는 새로 생성시 updated_at 컬럼도 생성된 시점의 날짜가 들어간다.
     */
    //    @LastModifiedDate
    //    @Column(name = "updated_at", nullable = true, updatable = false)
    //    private LocalDateTime updatedAt;
    @PreUpdate
    fun onUpdatedAt() {
        updatedAt = now()
    }
}
