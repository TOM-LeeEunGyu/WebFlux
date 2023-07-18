package io.dustin.apps.board.domain.restriction.posting.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostingRestriction is a Querydsl query type for PostingRestriction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostingRestriction extends EntityPathBase<PostingRestriction> {

    private static final long serialVersionUID = 856635730L;

    public static final QPostingRestriction postingRestriction = new QPostingRestriction("postingRestriction");

    public final io.dustin.apps.common.model.QBaseEntity _super = new io.dustin.apps.common.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> fromUserId = createNumber("fromUserId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postingId = createNumber("postingId", Long.class);

    public final NumberPath<Long> toUserId = createNumber("toUserId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPostingRestriction(String variable) {
        super(PostingRestriction.class, forVariable(variable));
    }

    public QPostingRestriction(Path<? extends PostingRestriction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostingRestriction(PathMetadata metadata) {
        super(PostingRestriction.class, metadata);
    }

}

