package com.hbrg.entity;

import com.hbrg.dto.BoardFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Board")
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 게시물 번호

    //    @ManyToOne
    @JoinColumn(name="huser_id")
    private String id; // 로그인 ID

    private String title; // 주제

    @Column(columnDefinition = "integer default 0")
    private int vc; // 조회수

    @ColumnDefault("0")
    private Long bLike; // 좋아요

    private LocalDateTime cDate; // 생성 날짜

    private LocalDateTime uDate; // 수정 날짜

    private String content;  // 본문

    public void updateBoard(String id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void updateBoard(BoardFormDto boardFormDto){
        this.boardId = boardFormDto.getBoardId();
        this.title = boardFormDto.getTitle();
        this.content = boardFormDto.getContent();;
    }

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setContent(boardFormDto.getContent());
        board.setTitle(boardFormDto.getTitle());
        return board;
    }

//    @Builder
//    public Board(String id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
}
