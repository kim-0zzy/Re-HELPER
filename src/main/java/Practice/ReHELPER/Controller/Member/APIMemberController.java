package Practice.ReHELPER.Controller.Member;


import Practice.ReHELPER.Controller.Member.Form.SighUpMemberForm;
import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.ExistMemberException;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMember")
public class APIMemberController {

    private final MemberService memberService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    private Long loadLoginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        return member.getId();
    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponseDTO> signUp(@RequestBody SighUpMemberForm sighUpMemberForm) throws ExistMemberException {
        if (memberService.validToNotDuplicatedMember(sighUpMemberForm.getUsername())) {
            throw new ExistMemberException("이미 존재하는 회원입니다. 다른 ID를 사용해주세요.");
        }
        Member member = new Member(sighUpMemberForm.getNickName(), sighUpMemberForm.getUsername(),
                passwordEncoder.encode(sighUpMemberForm.getPassword()));

        memberService.signIn(member);
        MemberDTO memberDTO = memberService.buildMemberDTO(member);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(new MessageResponseDTO("Rental Success", HttpStatus.CREATED.value(), memberDTO), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/findOne")
    public ResponseEntity<MessageResponseDTO> findOneMember(@RequestParam String memberName) throws NotFoundResultException {
        Optional<Member> member = memberService.findByUsername(memberName);

        member.orElseThrow(() -> new NotFoundResultException("Member is not Founded"));
        MemberDTO memberDTO = memberService.buildMemberDTO(member.get());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("find Success", HttpStatus.OK.value(), memberDTO), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<MessageResponseDTO> findAllMember(@RequestParam String memberName) throws NotFoundResultException {
        List<MemberDTO> memberDTOList = memberService.findAllMembers()
                .stream()
                .map(memberService::buildMemberDTO)
                .collect(Collectors.toList());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("find Success", HttpStatus.OK.value(), memberDTOList), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/loggedMember")
    public ResponseEntity<MessageResponseDTO> loggedMember() throws NotFoundResultException {
        Long loginMemberId = loadLoginMember();
        Optional<Member> member = memberService.findOneById(loginMemberId);

//        member.orElseThrow(() -> new NotFoundResultException("Member is not Founded"));
//        MemberDTO memberDTO = ;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("find Success", HttpStatus.OK.value(),
                memberService.buildMemberDTO(member.get())), httpHeaders, HttpStatus.OK);

    }
}
