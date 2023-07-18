package io.dustin.apps.board.domain.qna.answer.model;

import io.dustin.apps.common.code.YesOrNo;
import io.dustin.apps.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseEntity {

    @Builder
    public Answer(Long id, @NotNull String content, YesOrNo isDeleted, @NotNull Long questionId, @NotNull Long adminId) {
        // do something
        this.id = id;
        this.content = content;
        this.questionId = questionId;
        this.adminId = adminId;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    private YesOrNo isDeleted;

    /** 질문 엔티티 */
    @Column
    private Long questionId;

    /** 작성자 */
    @Column
    private Long adminId;


    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.isDeleted = YesOrNo.Y;
    }

}
