package io.dustin.apps.board.domain.community.posting.model;

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
public class Posting extends BaseEntity {

    @Builder
    public Posting(Long id, @NotNull String subject, @NotNull String content, YesOrNo isDeleted,
                   @NotNull Long userId, Long likeCount, Long clickCount, Long commentCount) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.userId = userId;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;
        this.likeCount = likeCount;
        this.clickCount = clickCount;
        this.commentCount = commentCount;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    private YesOrNo isDeleted;

    @Column(columnDefinition = "bigint default 0")
    private Long likeCount;

    @Column(columnDefinition = "bigint default 0")
    private Long clickCount;

    @Column(columnDefinition = "bigint default 0")
    private Long commentCount;

    private Long userId;

    public void updateSubject(String subject) {
        this.subject = subject;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.isDeleted = YesOrNo.Y;
    }

    public void setLikeCount(Long likeCount ) {this.likeCount = likeCount;}

    public void setCommentCount(Long commentCount ) {this.commentCount = commentCount;}

    public void setClickCount(Long clickCount) {this.clickCount = clickCount;}


}
