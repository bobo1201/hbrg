package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "Reply")
@Getter
@Setter
@ToString
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reId")
    private Long reId; // 댓글 번호


    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private HUser hUser; // 로그인 ID



    private String Nic;




    private int commentCnt;

    private int commentGroup;

    @ColumnDefault("0")
    private int commentSequence;

    @ColumnDefault("0")
    private int level;
/*
    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public String getReContent() {
        return reContent;
    }*/




}

