package io.dustin.apps.board.domain.bookmark.model

import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
class Bookmark (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,

    val boardId: Long,

) : BaseEntity()