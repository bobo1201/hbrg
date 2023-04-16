package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "HFile")
@Getter @Setter
@ToString
public class HFile {

    @Id
    @Column(name="fileId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId; // 파일 번호

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board; // board id 가져오기
//
//    @JoinColumn(name="boardId")
//    @Column(name="boardId", nullable = false)
//    private Long boardId; // 게시물 번호

    @Column(name="fileNm")
    private String fileNm; // 파일명

    @Column(name="oriFileNm")
    private String oriFileNm; // 원본 파일명

    @Column(name="fileUrl")
    private String fileUrl; // 이미지 경로

    private String repImgYn; // 대표 이미지 여부

    public void updateFile(String oriFileNm, String fileNm, String fileUrl){
        this.oriFileNm = oriFileNm;
        this.fileNm = fileNm;
        this.fileUrl = fileUrl;
    }

}
