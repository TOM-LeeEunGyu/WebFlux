package io.dustin.apps.board.domain.restriction.user.model;

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
public class UserRestriction {

    @Builder
    public UserRestriction(Long id, @NotNull Long fromUserId, @NotNull Long toUserId) {
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
