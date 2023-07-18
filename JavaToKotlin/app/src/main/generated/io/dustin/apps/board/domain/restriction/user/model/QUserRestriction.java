package io.dustin.apps.board.domain.restriction.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRestriction is a Querydsl query type for UserRestriction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRestriction extends EntityPathBase<UserRestriction> {

    private static final long serialVersionUID = -193185936L;

    public static final QUserRestriction userRestriction = new QUserRestriction("userRestriction");

    public final NumberPath<Long> fromUserId = createNumber("fromUserId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> toUserId = createNumber("toUserId", Long.class);

    public QUserRestriction(String variable) {
        super(UserRestriction.class, forVariable(variable));
    }

    public QUserRestriction(Path<? extends UserRestriction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRestriction(PathMetadata metadata) {
        super(UserRestriction.class, metadata);
    }

}

