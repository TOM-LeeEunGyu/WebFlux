package io.dustin.apps.common.model

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime = now()

    @Column(name = "updated_at", nullable = true, updatable = false)
    var updatedAt: LocalDateTime? = null

    @PreUpdate
    fun onUpdatedAt() {
        updatedAt = now()
    }

}
