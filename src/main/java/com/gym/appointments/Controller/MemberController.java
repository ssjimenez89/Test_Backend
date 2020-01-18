package com.gym.appointments.Controller;

import com.gym.appointments.Model.Member;
import com.gym.appointments.Repository.MemberRepository;
import com.gym.appointments.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @GetMapping("/member/{id}")
    public Member getMemberById(@PathVariable(value = "id") Integer coachId){
        return memberService.getMemberById(coachId);
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@Valid @RequestBody Member member){
        return memberService.add(member);
    }

    @PutMapping("/member/{id}")
    public Member editMember(@PathVariable(value = "id") Integer memberId, @Valid @RequestBody Member member){
        return memberService.edit(memberId, member);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable(value = "id") Integer memberId){
        memberService.delete(memberId);
    }
}
