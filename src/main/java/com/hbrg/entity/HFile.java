package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "File")
@Getter
@Setter
@ToString
public class HFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId; // 파일 번호

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board; // board id 가져오기

//    @OneToMany
//    @JoinColumn(name="id")
//    private String id; // 로그인 ID


    private String fileNm; // 파일명

    private String oriFileNm; // 원본 파일명

    private String fileUrl; // 이미지 경로

    private String repImgYn; // 대표 이미지 여부

    public void updateItemImg(String oriFileNm, String fileNm, String fileUrl){
        this.oriFileNm = oriFileNm;
        this.fileNm = fileNm;
        this.fileUrl = fileUrl;
    }
}
