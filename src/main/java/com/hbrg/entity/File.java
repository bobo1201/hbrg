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
    @Column(name="fileId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId; // 파일 번호

//    @OneToMany
    @JoinColumn(name="id")
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID

    @JoinColumn(name="boardId")
    @Column(name="boardId", nullable = false)
    private String boardId; // 게시물 번호

    @Column(name="fileNm")
    private String fileNm; // 파일명

    @Column(name="oriFileNm")
    private String oriFileNm; // 원본 파일명

    @Column(name="fileUrl")
    private String fileUrl; // 이미지 경로
}
