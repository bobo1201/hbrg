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

public class File {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId; // 파일 번호

//    @OneToMany
    @JoinColumn(name="id")
    private String id; // 로그인 ID

    @JoinColumn(name="boardId")
    private String boardId; // 게시물 번호

    private String fileNm; // 파일명

    private String oriFileNm; // 원본 파일명

    private String fileUrl; // 이미지 경로
}
