package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Tag")
@Getter
@Setter
@ToString
public class Tag {

    @Id
    @Column(name="tagId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId; // 해시태그 순서

    @Column(name="tagNm", nullable = false)
    private String tagNm; // 해시태그 이름
}
