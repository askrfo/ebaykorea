package examples.boot.myboard.service;

import examples.boot.myboard.domain.Member;

public interface MemberService {
    Member addMember(Member member);
    Member getMember(String email);
}
