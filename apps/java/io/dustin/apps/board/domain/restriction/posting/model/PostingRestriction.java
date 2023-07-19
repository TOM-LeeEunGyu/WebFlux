package io.dustin.apps.board.domain.restriction.posting.model;

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
public class PostingRestriction extends BaseEntity {

    @Builder
    public PostingRestriction(Long id, @NotNull Long fromUserId, @NotNull Long toUserId, @NotNull Long postingId) {
        this.id =id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.postingId = postingId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "posting_id")
    private Long postingId;


}
