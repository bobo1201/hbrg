package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "HUser")
@Getter
@Setter
@ToString
public class HUser {

    @Id
    @Column(nullable = false)
    private String id; // 로그인 ID

    @Column(nullable = false)
    private String pw; // 로그인 PW

    @Column(nullable = false)
    private String em; // 회원가입 이메일

    private String nic; // 회원가입 닉네임

    private Long ph; // 회원가입 전화번호

    private String ad; // 회원가입 주소
}
