package Practice.ReHELPER.Service;

import Practice.ReHELPER.Controller.MemberSpec.Form.UpdateMemberSpecForm;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.Routine;

public interface MemberSpecService {
    void saveMemberSpec(MemberSpec memberSpec);
    MemberSpec createMemberSpec(MemberSpec memberSpec);
    void updateBasicMemberSpec(MemberSpec memberSpec, UpdateMemberSpecForm updateMemberSpecForm);
    MemberSpec findMemberSpecByMemberId(Long id);
    MemberSpec findMemberSpec(Long id);
    Routine createRoutine(MemberSpec memberSpec);
    void updateRoutine(MemberSpec memberSpec);


}
