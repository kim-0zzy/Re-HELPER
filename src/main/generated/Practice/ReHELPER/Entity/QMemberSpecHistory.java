package Practice.ReHELPER.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSpecHistory is a Querydsl query type for MemberSpecHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSpecHistory extends EntityPathBase<MemberSpecHistory> {

    private static final long serialVersionUID = 464373198L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSpecHistory memberSpecHistory = new QMemberSpecHistory("memberSpecHistory");

    public final NumberPath<Integer> his_career = createNumber("his_career", Integer.class);

    public final NumberPath<Integer> his_weight = createNumber("his_weight", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> make_date = createDate("make_date", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> make_date_withTime = createDateTime("make_date_withTime", java.time.LocalDateTime.class);

    public final QMemberSpec memberSpec;

    public QMemberSpecHistory(String variable) {
        this(MemberSpecHistory.class, forVariable(variable), INITS);
    }

    public QMemberSpecHistory(Path<? extends MemberSpecHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSpecHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSpecHistory(PathMetadata metadata, PathInits inits) {
        this(MemberSpecHistory.class, metadata, inits);
    }

    public QMemberSpecHistory(Class<? extends MemberSpecHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberSpec = inits.isInitialized("memberSpec") ? new QMemberSpec(forProperty("memberSpec"), inits.get("memberSpec")) : null;
    }

}

