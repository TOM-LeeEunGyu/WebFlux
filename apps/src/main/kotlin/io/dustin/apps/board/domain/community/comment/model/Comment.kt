package io.dustin.apps.board.domain.community.comment.model

import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class Comment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    var isDeleted: YesOrNo = YesOrNo.N,

    val userId: Long,

    val replyId: Long? = null,

    val postingId: Long,

    ) : BaseEntity() {

    @Column(columnDefinition = "bigint default 0")
    var likeCount: Long = 0

    @Column(columnDefinition = "bigint default 0")
    var clickCount: Long = 0


    fun updateContent(content: String) {
        this.content = content
    }

    fun delete() {
        this.isDeleted = YesOrNo.Y
    }

    fun updateLikeCount(likeCount: Long) {
        this.likeCount = likeCount
    }

}