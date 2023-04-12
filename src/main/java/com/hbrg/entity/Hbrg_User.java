package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Hbrg_User")
@Getter
@Setter
@ToString
public class Hbrg_User {

    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId; // 아이디 순서

    @Id
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID


    @Column(name="pw", nullable = false)
    private String pw; // 로그인 PW

    @Column(name="em", nullable = false)
    private String em; // 회원가입 이메일

    @Column(name="nic", nullable = false)
    private String nic; // 회원가입 닉네임

    @Column(name="ph")
    private Long ph; // 회원가입 전화번호

    @Column(name="ad")
    private String ad; // 회원가입 주소
}
