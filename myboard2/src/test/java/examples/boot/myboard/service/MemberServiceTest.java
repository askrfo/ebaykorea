package examples.boot.myboard.service;

import examples.boot.myboard.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    public void testAddMember() throws Exception{
        System.out.println("------------------------------");
        Member member = new Member();
        member.setName("kim");
        member.setRegdate(LocalDateTime.now());
        member.setEmail("urstory@gmail.com");
        member.setPasswd("1234");
        member = memberService.addMember(member);
        System.out.println(member.getId());
        System.out.println("------------------------------");
    }
}
