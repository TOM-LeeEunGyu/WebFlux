package io.dustin.apps.admin.domain.notice.model;

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
public class Notice extends BaseEntity {
    /**
     * 공지 관련은 어드민 유저만 가능하다.
     */

    @Builder
    public Notice(Long id, @NotNull String subject, @NotNull String content, YesOrNo isDeleted, @NotNull Long adminId, Long clickCount, Long likeCount){
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.adminId = adminId;
        this.isDeleted = isDeleted == null ? YesOrNo.N : isDeleted;
        this.likeCount = likeCount;
        this.clickCount = clickCount;

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

    private Long adminId;


    public void updateSubject(String subject) {
        this.subject = subject;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void setClickCount(Long clickCount) {this.clickCount = clickCount;}

    public void setLikeCount(Long likeCount ) {this.likeCount = likeCount;}


    public void delete() {
        this.isDeleted = YesOrNo.Y;
    }


}


