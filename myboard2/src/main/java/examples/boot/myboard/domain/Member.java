package examples.boot.myboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String passwd;
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MemberRole> memberRoles = new HashSet<>();

    // helper method
    public void addMemberRole(MemberRole memberRole){
        memberRoles.add(memberRole);
        if(memberRole.getMember() != this){
            memberRole.setMember(this);
        }
    }
}
