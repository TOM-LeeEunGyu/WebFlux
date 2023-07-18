package io.dustin.apps.board.domain.blockeduser.model;

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
public class BlockedUser extends BaseEntity {

    @Builder
    public BlockedUser(Long id, @NotNull Long fromUserId, @NotNull Long toUserId){
        this.id =id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;
}
