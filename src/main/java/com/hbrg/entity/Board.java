package com.hbrg.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Board")
@Getter
@Setter
@ToString
public class Board {

    @Id
    @Column(name="boardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 게시물 번호

//    @OneToMany
    @JoinColumn(name="id")
    @ManyToOne(fetch = FetchType.LAZY)
    private String id; // 로그인 ID

    @Column(length = 500, nullable = false)
    private String title; // 주제

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;  // 본문

    @Column(columnDefinition = "integer default 0")
    private Long vC; // 조회수


    private Long bLike; // 좋아요

    private String writer; // 작성자

    @Column(name="cDate")
    private LocalDateTime cDate; // 생성 날짜

    @Column(name="uDate")
    private LocalDateTime uDate; // 수정 날짜

    @OneToMany(mappedBy = "Board", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("boardId asc") // 댓글 정렬
    private List<Reply> reply;

    @Builder
    public Board(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

