package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Hbrg_Board")
@Getter
@Setter
@ToString
public class Hbrg_Board {

    @Id
    @Column(name="BoardId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BoardId; // 게시글 순서

//    @OneToMany
    @JoinColumn(name="Id")
    @Column(name="Id", nullable = false)
    private String Id; // 로그인 ID

    @Column(name="Title", nullable = false)
    private String Title; // 주제

    @Column(name="VC", nullable = false)
    private Long VC; // 조회수

    @Column(name="Like", nullable = false)
    private Long Like; // 좋아요

    @Column(name="CDate")
    private LocalDateTime CDate; // 생성 날짜

    @Column(name="UDate")
    private LocalDateTime UDate; // 수정 날짜

    @Column(name="Txt")
    private String Txt;  // 본문

}
