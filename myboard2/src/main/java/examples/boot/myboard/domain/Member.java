package examples.boot.myboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String passwd;
    private LocalDateTime regdate;

    @JsonIgnore
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
