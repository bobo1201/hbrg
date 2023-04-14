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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reReId; // 대댓글 번호

    @JoinColumn(name="reId")
    private Long reId; // 댓글 번호

//    @OneToMany
    @JoinColumn(name="id")
    private String id; // 로그인 ID

    private LocalDateTime reReCDate; // 대댓글 생성 날짜

    private String reReTxt; // 대댓글 내용
}
