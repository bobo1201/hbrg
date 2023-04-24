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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardTagId; // 게시물 태그 매핑 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardId")
    private Board board; // 게시물 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tagId")
    private Tag tag; // 해시태그 번호
}
