package Practice.ReHELPER.Controller.Member;


import Practice.ReHELPER.Controller.Member.Form.ChangePasswordForm;
import Practice.ReHELPER.Controller.Member.Form.SighUpMemberForm;
import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.ExistMemberException;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Exception.PasswordException;
import Practice.ReHELPER.Service.MemberService;
import jakarta.validation.Valid;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMember")
public class APIMemberController {

    private final MemberService memberService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Member loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Yet Logged in");
        }
        return (Member) authentication.getPrincipal();
    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponseDTO> signUp(@RequestBody SighUpMemberForm sighUpMemberForm)
            throws ExistMemberException {
        if (memberService.validToNotDuplicatedMember(sighUpMemberForm.getUsername())) {
            throw new ExistMemberException("이미 존재하는 회원입니다. 다른 ID를 사용해주세요.");
        }
        Member member = new Member(sighUpMemberForm.getNickName(), sighUpMemberForm.getUsername(),
                passwordEncoder.encode(sighUpMemberForm.getPassword()));

        memberService.signIn(member);
        MemberDTO memberDTO = memberService.buildMemberDTO(member);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(new MessageResponseDTO("SignUp Success", HttpStatus.CREATED.value(), memberDTO), httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/findOne")
    public ResponseEntity<MessageResponseDTO> findOneMember(@RequestParam String memberName)
            throws NotFoundResultException {
        MemberDTO memberDTO = memberService.findByUsername(memberName);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(), memberDTO), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<MessageResponseDTO> findAllMember()
            throws NotFoundResultException {
        List<MemberDTO> memberDTOList = memberService.findAllMembers();

        if (memberDTOList.isEmpty()) {
            throw new NotFoundResultException("List is Empty");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(), memberDTOList), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/loggedMember")
    public ResponseEntity<MessageResponseDTO> loggedMember()
            throws NotLoggedInException, NotFoundResultException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        MemberDTO memberDTO = memberService.findOneById(loadLoginMember().getId());

        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                memberDTO), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<MessageResponseDTO> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm)
            throws NotLoggedInException, PasswordException {

        String message = memberService.updatePassword(loadLoginMember(), changePasswordForm.getPresentPassword(), changePasswordForm.getChangePassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Update Success", HttpStatus.OK.value(),
                message), httpHeaders, HttpStatus.OK);
    }
}
