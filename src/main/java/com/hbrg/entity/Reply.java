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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reId; // 댓글 번호

    @JoinColumn(name="boardId")
    private Long boardId; // 게시물 번호

//    @OneToMany
    @JoinColumn(name="id")
    private String id; // 로그인 ID

    private LocalDateTime reCDate; // 댓글 생성 날짜

    private String reTxt; // 댓글 내용
}
