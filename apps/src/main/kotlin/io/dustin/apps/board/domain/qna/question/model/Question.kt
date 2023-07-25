package io.dustin.apps.board.domain.qna.question.model

import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.type.descriptor.converter.spi.EnumValueConverter

@Entity
class Question (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    var isDeleted: YesOrNo = YesOrNo.N,

    val userId: Long,

    var subject: String,

    var content: String,


) : BaseEntity() {

    @Column(columnDefinition = "bigint default 0")
    var clickCount: Long = 0


    fun updateSubject(subject: String) {
        this.subject = subject
    }

    fun updateContent(content: String) {
        this.content = content
    }

    fun delete() {
        isDeleted = YesOrNo.Y
    }

    fun updateClickCount(clickCount: Long) {
        this.clickCount = clickCount
    }
}
