package Practice.ReHELPER.Service;

import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.Routine;

public interface MemberSpecService {
    public Long saveMemberSpec(MemberSpec memberSpec);
    public MemberSpec createMemberSpec(MemberSpec memberSpec);
    public void updateBasicMemberSpec(MemberSpec memberSpec, int height, int weight, int waist, int hip, int career, int age, int times, Gender gender, Goals goals);
    public Routine createRoutine(MemberSpec memberSpec);
    public void updateRoutine(MemberSpec memberSpec);
    public MemberSpec findMemberSpecByMemberId(Long id);
    public MemberSpec findMemberSpec(Long id);



}
