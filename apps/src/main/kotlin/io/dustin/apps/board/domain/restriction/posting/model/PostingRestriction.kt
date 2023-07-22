package io.dustin.apps.board.domain.restriction.posting.model

import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*

@Entity
class PostingRestriction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "from_user_id")
    val fromUserId: Long,

    @Column(name = "to_user_id")
    val toUserId: Long,


    @Column(name = "posting_id")
    val postingId: Long,



    ) : BaseEntity()
