package io.dustin.apps.board.domain.community.comment.model;

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
public class Comment extends BaseEntity {

    @Builder
    public Comment(Long id, @NotNull String content, YesOrNo isDeleted,@NotNull Long userId, @NotNull Long postingId,
                   Long likeCount, Long replyId, Long clickCount) {
        this.id = id;
        this.content = content;
        this.postingId = postingId;
        this.userId = userId;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;
        this.likeCount = likeCount;
        this.replyId = replyId == null ? null : replyId;
        this.clickCount = clickCount;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    private YesOrNo isDeleted;

    @Column(columnDefinition = "bigint default 0")
    private Long likeCount;

    @Column(columnDefinition = "bigint default 0")
    private Long clickCount;

    /** 작성자 */
    private Long userId;

    private Long postingId;

    private Long replyId;

    public void updateContent(String content) {
        this.content = content;
    }

    public void delete() {
        this.isDeleted = YesOrNo.Y;
    }

    public void setLikeCount(Long likeCount ) {this.likeCount = likeCount;}



}