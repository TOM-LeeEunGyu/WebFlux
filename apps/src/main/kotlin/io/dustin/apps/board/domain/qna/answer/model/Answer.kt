package io.dustin.apps.board.domain.qna.answer.model

import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*

@Entity
class Answer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    var isDeleted: YesOrNo = YesOrNo.N,

    var questionId: Long,

    val adminId: Long,

    var content: String,

) : BaseEntity() {


    fun updateContent(content: String) {
        this.content = content
    }

    fun delete() {
        isDeleted = YesOrNo.Y
    }


}
