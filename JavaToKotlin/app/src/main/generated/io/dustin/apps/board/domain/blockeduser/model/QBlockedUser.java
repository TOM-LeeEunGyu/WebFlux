package io.dustin.apps.board.domain.blockeduser.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockedUser is a Querydsl query type for BlockedUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockedUser extends EntityPathBase<BlockedUser> {

    private static final long serialVersionUID = 1582570118L;

    public static final QBlockedUser blockedUser = new QBlockedUser("blockedUser");

    public final io.dustin.apps.common.model.QBaseEntity _super = new io.dustin.apps.common.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> fromUserId = createNumber("fromUserId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> toUserId = createNumber("toUserId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBlockedUser(String variable) {
        super(BlockedUser.class, forVariable(variable));
    }

    public QBlockedUser(Path<? extends BlockedUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockedUser(PathMetadata metadata) {
        super(BlockedUser.class, metadata);
    }

}

