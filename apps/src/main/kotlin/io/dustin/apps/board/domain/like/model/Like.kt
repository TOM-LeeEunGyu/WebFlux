package io.dustin.apps.board.domain.like.model

import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "like_it")
class Like (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "board_id")
    val boardId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val boardType: BoardType,





) : BaseEntity()



