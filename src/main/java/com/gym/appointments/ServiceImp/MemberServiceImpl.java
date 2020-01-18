package com.gym.appointments.ServiceImp;

import com.gym.appointments.Model.Member;
import com.gym.appointments.Repository.MemberRepository;
import com.gym.appointments.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Override
    public Member add(Member member) {
        LocalDate ld = LocalDate.now();
        member.setRegistrationDate(ld.toString());
        return memberRepository.save(member);
    }

    @Override
    public Member edit(Integer memberId, Member memberNew) {
        Member member = memberRepository.findById(memberId).get();
        member.setName(memberNew.getName());
        member.setFirstSurname(memberNew.getFirstSurname());
        member.setSecondSurname(memberNew.getSecondSurname());
        member.setAge(memberNew.getAge());
        member.setSex(memberNew.getSex());
        member.setPhone(memberNew.getPhone());
        return memberRepository.save(member);
    }

    @Override
    public void delete(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
