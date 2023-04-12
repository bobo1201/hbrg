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
    @Column(name="boardId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId; // 게시물 번호
<<<<<<< HEAD

=======
>>>>>>> origin/Jin
    
//    @OneToMany
    @JoinColumn(name="id")
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="title", nullable = false)
    private String title; // 주제

    @Column(name="vC", nullable = false)
    private Long vC; // 조회수

    @Column(name="like", nullable = false)
    private Long like; // 좋아요

    @Column(name="cDate")
    private LocalDateTime cDate; // 생성 날짜

    @Column(name="uDate")
    private LocalDateTime uDate; // 수정 날짜

    @Column(name="txt")
    private String txt;  // 본문
}
