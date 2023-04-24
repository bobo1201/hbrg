package com.hbrg.entity;

import com.hbrg.dto.BoardFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Board")
@Getter
@Setter
@ToString
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 게시물 번호

    //    @ManyToOne
//    @JoinColumn(name="huser_id")
//    private String id; // 로그인 ID

    @ManyToOne
    @JoinColumn(name="id")
    private Huser user;

    private String title; // 주제

    // 조회수를 위한 디폴트 값 설정
    @Column(columnDefinition = "integer default 0")
    private int vc; // 조회수

    @Column
    private int bLike; // 좋아요

    @OneToMany(mappedBy = "board")
    private List<Likes> likes = new ArrayList<>();

    private String content;  // 본문


    // 글삭제, 파일 의존성 추가(23/04/18 16:58)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hfile> files = new ArrayList<>();

    // 댓글 의존성 추가(23/04/18 16:58)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    //    // 해시태그(23/04/21 20:53)
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BoardTag> boardTags = new ArrayList<>();


    public void removeHFiles() {
        files.clear();
    }

    public void updateBoard(BoardFormDto boardFormDto){
        this.title = boardFormDto.getTitle();
        this.content = boardFormDto.getContent();;
        this.boardId = boardFormDto.getBoardId();
    }

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setContent(boardFormDto.getContent());
        board.setTitle(boardFormDto.getTitle());
        return board;
    }

}
