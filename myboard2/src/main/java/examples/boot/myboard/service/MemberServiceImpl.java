package examples.boot.myboard.service;

import examples.boot.myboard.domain.Member;
import examples.boot.myboard.domain.MemberRole;
import examples.boot.myboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional
    public Member addMember(Member member) {
        // member의 암호를 암호화한다.
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories
                        .createDelegatingPasswordEncoder();

        String encodePaasswd = passwordEncoder.encode(member.getPasswd());
        System.out.println("encode : :" + encodePaasswd);
        member.setPasswd(encodePaasswd);

        // MemberRole("USER")을 Member에 추가한다.
        MemberRole memberRole = new MemberRole();
        memberRole.setName("USER");
        member.addMemberRole(memberRole); // helper method 이용
        // Member를 저장한다.

        // member가 영속성이 부여될 때 MembeRole도 영속성이 부여되야 한다.
        member = memberRepository.save(member);
        // Member를 리턴한다.
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }
}
