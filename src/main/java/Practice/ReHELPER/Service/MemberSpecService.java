package Practice.ReHELPER.Service;

import Practice.ReHELPER.Controller.MemberSpec.Form.UpdateMemberSpecForm;
import Practice.ReHELPER.DTO.MemberSpecDTO;
import Practice.ReHELPER.DTO.RoutineDTO;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.Embedded.Nutrition;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.Embedded.Routine;

import java.util.HashMap;

public interface MemberSpecService {
    void saveMemberSpec(MemberSpec memberSpec);
    MemberSpec createMemberSpec(MemberSpec memberSpec, Member member);
    void updateBasicMemberSpec(MemberSpec memberSpec, UpdateMemberSpecForm updateMemberSpecForm);
    Long resetMemberSpec(Long id);

    MemberSpec findMemberSpecByMemberId(Long id);
    MemberSpecDTO findMemberSpecDTOByMemberId(Long id);
    RoutineDTO findRoutineDTOByMemberId(Long id);

    Routine makeRoutine(MemberSpec memberSpec);
    Nutrition makeNutrition(MemberSpec memberSpec, Double bmr);
    HashMap<String, Object> makePartition(Integer times, Goals goals, Level level);
    Level makeLevel(Double career);
    Double makeBMR(MemberSpec memberSpec);

    void increaseCareer(MemberSpec memberSpec);
    void decreaseCareer(MemberSpec memberSpec);


    MemberSpecDTO buildMemberSpec(MemberSpec memberSpec);
    RoutineDTO buildRoutine(MemberSpec memberSpec);
}
