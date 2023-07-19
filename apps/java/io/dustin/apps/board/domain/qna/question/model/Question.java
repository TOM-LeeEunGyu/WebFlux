package io.dustin.apps.board.domain.qna.question.model;

import io.dustin.apps.common.code.YesOrNo;
import io.dustin.apps.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {

    @Builder
    public Question(Long id, @NotNull Long userId, @NotNull String subject, @NotNull String content, YesOrNo isDeleted, Long clickCount) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.content = content;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;
        this.clickCount = clickCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    private YesOrNo isDeleted;

    @Column(columnDefinition = "bigint default 0")
    private Long clickCount;





    public void updateSubject(String subject) {
        this.subject = subject;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.isDeleted = YesOrNo.Y;
    }

    public void setClickCount(Long clickCount) {this.clickCount = clickCount;}



}
