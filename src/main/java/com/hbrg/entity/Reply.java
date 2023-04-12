package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reply")
@Getter
@Setter
@ToString
public class Reply {

    @Id
    @Column(name="reId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reId; // 댓글 번호

    @JoinColumn(name="boardId")
    @Column(name="boardId", nullable = false)
    private Long boardId; // 게시물 번호

//    @OneToMany
    @JoinColumn(name="id")
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="reCDate")
    private LocalDateTime reCDate; // 댓글 생성 날짜

    @Column(name="reContent")
    private String reContent; // 댓글 내용
}
