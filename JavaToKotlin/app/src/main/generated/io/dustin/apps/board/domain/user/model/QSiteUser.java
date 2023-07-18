package io.dustin.apps.board.domain.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSiteUser is a Querydsl query type for SiteUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSiteUser extends EntityPathBase<SiteUser> {

    private static final long serialVersionUID = 493095877L;

    public static final QSiteUser siteUser = new QSiteUser("siteUser");

    public final io.dustin.apps.common.model.QBaseEntity _super = new io.dustin.apps.common.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> follower = createNumber("follower", Long.class);

    public final NumberPath<Long> following = createNumber("following", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<io.dustin.apps.common.code.YesOrNo> isBaned = createEnum("isBaned", io.dustin.apps.common.code.YesOrNo.class);

    public final EnumPath<io.dustin.apps.common.code.YesOrNo> isDeleted = createEnum("isDeleted", io.dustin.apps.common.code.YesOrNo.class);

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath profile = createString("profile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSiteUser(String variable) {
        super(SiteUser.class, forVariable(variable));
    }

    public QSiteUser(Path<? extends SiteUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSiteUser(PathMetadata metadata) {
        super(SiteUser.class, metadata);
    }

}

