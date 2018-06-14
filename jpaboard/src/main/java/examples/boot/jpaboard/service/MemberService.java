package examples.boot.jpaboard.service;

import examples.boot.jpaboard.domain.Member;

import java.util.List;

public interface MemberService {
    public Member getMember(Long id);
    public Member addMember(Member member);
    public List<Member> getMembers();
    public Member getMemberByEmail(String email);
}
