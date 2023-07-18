package io.dustin.apps.board.domain.blockeduser.model

import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class BlockedUser(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "from_user_id")
    val fromUserId: Long,

    @Column(name = "to_user_id")
    val toUserId: Long,

): BaseEntity()
