package com.hbrg.entity;

import com.hbrg.dto.BoardFormDto;
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
    @JoinColumn(name="huserid")
    private String id; // 로그인 ID

    @Column()
    private String title; // 주제

    @ColumnDefault("0")
    private Long vC; // 조회수

    @ColumnDefault("0")
    private Long bLike; // 좋아요

    private LocalDateTime cDate; // 생성 날짜

    private LocalDateTime uDate; // 수정 날짜

    private String txt;  // 본문

    public void updateBoard(String id, String title, String txt){
        this.id = id;
        this.title = title;
        this.txt = txt;
    }

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setTxt(boardFormDto.getTxt());
        board.setTitle(boardFormDto.getTitle());
        return board;
    }

}
