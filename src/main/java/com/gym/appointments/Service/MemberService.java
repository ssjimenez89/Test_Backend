package com.gym.appointments.Service;

import com.gym.appointments.Model.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member getMemberById(Integer memberId);

    Member add(Member member);

    Member edit(Integer memberId, Member member);

    void delete(Integer memberId);
}
