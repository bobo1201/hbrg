package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Board_Tag")
@Getter
@Setter
@ToString
public class BoardTag {

    @Id
    @Column(name="boardTagId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardTagId; // 게시물 태그 매핑 번호

    @JoinColumn(name="boardId")
    @Column(name="boardId", nullable = false)
    private Long boardId; // 게시물 번호

    @Column(name="tagId", nullable = false)
    private String tagId; // 해시태그 번호

}
