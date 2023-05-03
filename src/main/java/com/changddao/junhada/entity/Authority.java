package com.changddao.junhada.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {
    @Id @GeneratedValue
    private Long id;
    private String memberRole;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Authority(String memberRole) {
        this.memberRole = memberRole;
    }

    //연관관계 메서드
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getRoles().remove(this);
        }
        this.member=member;
        member.getRoles().add(this);
    }

}
