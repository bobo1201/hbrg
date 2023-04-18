package com.hbrg.entity;

import com.hbrg.dto.BoardFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String id; // 로그인 ID

//    @ManyToOne
//    @JoinColumn(name="huser_id")
//    private HUser hUser;

    private String title; // 주제

    // 조회수를 위한 디폴트 값 설정
    @Column(columnDefinition = "integer default 0")
    private int vc; // 조회수

    @Column(columnDefinition = "integer default 0")
    private int bLike; // 좋아요

    private String content;  // 본문

    // 글삭제(23/04/18 16:58)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HFile> hFiles = new ArrayList<>();

    public void removeHFiles() {
        hFiles.clear();
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
