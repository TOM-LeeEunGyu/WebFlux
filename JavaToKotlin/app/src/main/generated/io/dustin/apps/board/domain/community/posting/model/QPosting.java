package io.dustin.apps.board.domain.community.posting.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPosting is a Querydsl query type for Posting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosting extends EntityPathBase<Posting> {

    private static final long serialVersionUID = 442912055L;

    public static final QPosting posting = new QPosting("posting");

    public final io.dustin.apps.common.model.QBaseEntity _super = new io.dustin.apps.common.model.QBaseEntity(this);

    public final NumberPath<Long> clickCount = createNumber("clickCount", Long.class);

    public final NumberPath<Long> commentCount = createNumber("commentCount", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<io.dustin.apps.common.code.YesOrNo> isDeleted = createEnum("isDeleted", io.dustin.apps.common.code.YesOrNo.class);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final StringPath subject = createString("subject");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPosting(String variable) {
        super(Posting.class, forVariable(variable));
    }

    public QPosting(Path<? extends Posting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPosting(PathMetadata metadata) {
        super(Posting.class, metadata);
    }

}

