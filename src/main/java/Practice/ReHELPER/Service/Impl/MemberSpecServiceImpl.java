package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.Controller.MemberSpec.Form.UpdateMemberSpecForm;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.Routine;
import Practice.ReHELPER.Repository.MemberSpecRepository;
import Practice.ReHELPER.Service.MemberSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSpecServiceImpl implements MemberSpecService {

    private final MemberSpecRepository memberSpecRepository;
    @Override
    public void saveMemberSpec(MemberSpec memberSpec) {
        memberSpecRepository.save(memberSpec);
    }

    @Override
    public MemberSpec createMemberSpec(MemberSpec memberSpec) {
        Routine routine = createRoutine(memberSpec);
        memberSpec.setRoutine(routine);
        return memberSpec;
    }

    @Override
    public void updateBasicMemberSpec(MemberSpec memberSpec, UpdateMemberSpecForm updateMemberSpecForm) {
        memberSpec.setBasicInfo(updateMemberSpecForm.getHeight(), updateMemberSpecForm.getWeight(), updateMemberSpecForm.getWaist(),
                updateMemberSpecForm.getHip(), updateMemberSpecForm.getCareer(), updateMemberSpecForm.getAge());
        memberSpec.setTimes(updateMemberSpecForm.getTimes());
        memberSpec.setGoals(updateMemberSpecForm.getGoals());
        memberSpec.setGender(updateMemberSpecForm.getGender());
        memberSpec.setLevel(memberSpec.makeLevel());
        updateRoutine(memberSpec);
    }

    @Override
    public MemberSpec findMemberSpecByMemberId(Long id) {
        return memberSpecRepository.findByMemberId(id);
    }

    @Override
    public MemberSpec findMemberSpec(Long id) {
        return memberSpecRepository.findById(id);
    }

    @Override
    public Routine createRoutine(MemberSpec memberSpec) {
        Routine routine = new Routine();
        // makePartition / makeNutrition
        return routine;
    }

    @Override
    public void updateRoutine(MemberSpec memberSpec) {

    }
}
