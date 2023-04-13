package com.hbrg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ReReply")
@Getter
@Setter
@ToString
public class ReReply {

    @Id
    @Column(name="reReId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reReId; // 대댓글 번호

    @JoinColumn(name="reId")
    @Column(name="reId", nullable = false)
    private Long reId; // 댓글 번호

//    @OneToMany
    @JoinColumn(name="id")
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="reReCDate")
    private LocalDateTime reReCDate; // 대댓글 생성 날짜

    @Column(name="reReTxt")
    private String reReTxt; // 대댓글 내용
}
