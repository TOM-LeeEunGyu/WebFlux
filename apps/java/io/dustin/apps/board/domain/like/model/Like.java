package io.dustin.apps.board.domain.like.model;

import io.dustin.apps.common.code.BoardType;
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
@Table(name = "like_it")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {

    @Builder
    public Like(Long id, @NotNull Long userId, @NotNull Long boardId,  @NotNull BoardType boardType){
        this.id =id;
        this.userId = userId;
        this.boardId = boardId;
        this.boardType = boardType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "board_id")
    private Long boardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BoardType boardType;
}
