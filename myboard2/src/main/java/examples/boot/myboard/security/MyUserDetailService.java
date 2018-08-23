package examples.boot.myboard.security;

import examples.boot.myboard.domain.Member;
import examples.boot.myboard.domain.MemberRole;
import examples.boot.myboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    MemberService memberService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Member member = memberService.getMember(email);
        if(member == null){
            throw new UsernameNotFoundException("user name not found : " + email);
        }

        // 레이지로딩.
        Set<MemberRole> memberRoles = member.getMemberRoles();
        List<GrantedAuthority> list = new ArrayList<>();
        for(MemberRole memberRole: memberRoles) {
            list.add(new SimpleGrantedAuthority(
                    "ROLE_" + memberRole.getName()));
        }
        UserDetails user =
                new User(email, member.getPasswd(),
                        list);
        return user;
    }
}
