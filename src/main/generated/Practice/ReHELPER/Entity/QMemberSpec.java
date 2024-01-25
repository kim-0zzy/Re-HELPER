package Practice.ReHELPER.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSpec is a Querydsl query type for MemberSpec
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSpec extends EntityPathBase<MemberSpec> {

    private static final long serialVersionUID = -2112935962L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSpec memberSpec = new QMemberSpec("memberSpec");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final ListPath<Calendar, QCalendar> calendar = this.<Calendar, QCalendar>createList("calendar", Calendar.class, QCalendar.class, PathInits.DIRECT2);

    public final NumberPath<Integer> career = createNumber("career", Integer.class);

    public final EnumPath<Practice.ReHELPER.Entity.E_type.Gender> gender = createEnum("gender", Practice.ReHELPER.Entity.E_type.Gender.class);

    public final EnumPath<Practice.ReHELPER.Entity.E_type.Goals> goals = createEnum("goals", Practice.ReHELPER.Entity.E_type.Goals.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Integer> hip = createNumber("hip", Integer.class);

    public final ListPath<MemberSpecHistory, QMemberSpecHistory> history = this.<MemberSpecHistory, QMemberSpecHistory>createList("history", MemberSpecHistory.class, QMemberSpecHistory.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Practice.ReHELPER.Entity.E_type.Level> level = createEnum("level", Practice.ReHELPER.Entity.E_type.Level.class);

    public final QMember member;

    public final QRoutine routine;

    public final NumberPath<Integer> times = createNumber("times", Integer.class);

    public final NumberPath<Integer> waist = createNumber("waist", Integer.class);

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QMemberSpec(String variable) {
        this(MemberSpec.class, forVariable(variable), INITS);
    }

    public QMemberSpec(Path<? extends MemberSpec> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSpec(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSpec(PathMetadata metadata, PathInits inits) {
        this(MemberSpec.class, metadata, inits);
    }

    public QMemberSpec(Class<? extends MemberSpec> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.routine = inits.isInitialized("routine") ? new QRoutine(forProperty("routine"), inits.get("routine")) : null;
    }

}

