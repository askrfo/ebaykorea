package examples.boot.myboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_role")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class MemberRole {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // helper
    public void setMember(Member member){
        this.member = member;
        if(!member.getMemberRoles().contains(this)){
            member.getMemberRoles().add(this);
        }
    }
}
