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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId; // 해시태그 순서

    @Column(unique = true, nullable = false)
    private String tagNm; // 해시태그 이름
}
