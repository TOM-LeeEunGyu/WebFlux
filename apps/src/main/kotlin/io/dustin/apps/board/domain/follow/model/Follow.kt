package io.dustin.apps.board.domain.follow.model

import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class Follow (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "follower_id")
    val followerId: Long,

    @Column(name = "following_id")
    val followingId: Long,


) : BaseEntity()
