package io.dustin.apps.board.domain.user.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.board.domain.user.model.dto.LikeItUserDto;
import io.dustin.apps.board.domain.user.repository.custom.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.types.Projections.bean;
import static io.dustin.apps.board.domain.like.model.QLike.like;
import static io.dustin.apps.board.domain.user.model.QSiteUser.siteUser;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory query;

    @Override
    public List<LikeItUserDto> findAllLikeItUser(Long boardId, BoardType boardType, Pageable pageable) {

        JPAQuery<LikeItUserDto> jPAQuery = query.select(bean(LikeItUserDto.class,
                    siteUser.id,
                    siteUser.nickName
                ))
                .from(siteUser)
                .innerJoin(like).on(siteUser.id.eq(like.userId))
                .where(
                    like.boardId.eq(boardId),
                    like.boardType.eq(boardType)
                )
                .orderBy(siteUser.nickName.asc());
        if(pageable != null) {
            jPAQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());

        }
        return jPAQuery.fetch();
    }

    /**
     * 추후 개발 예정
     */
    @Override
    public List<LikeItUserDto> findAllLikeItUserNoOffSet(Long boardId, BoardType boardType, Pageable pageable) {

        JPAQuery<LikeItUserDto> jPAQuery = query.select(bean(LikeItUserDto.class,
                        siteUser.id,
                        siteUser.nickName
                ))
                .from(siteUser)
                .innerJoin(like).on(siteUser.id.eq(like.userId))
                .where(
                        like.boardId.eq(boardId),
                        like.boardType.eq(boardType)
                )
                .orderBy(siteUser.nickName.asc());
        if(pageable != null) {
            jPAQuery.limit(pageable.getPageSize());


        }
        return jPAQuery.fetch();
    }




}
