package com.hbrg.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Replys")
@Getter
@Setter
@ToString
public class Reply {

    @Id
    @Column(name="reId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reId; // 댓글 번호

    @ManyToOne
    @JoinColumn(name="boardId")
    @Column(name="boardId", nullable = false) // 나중에 뺼것
    private Long boardId; // 게시물 번호

//    @OneToMany
    @ManyToOne
    @JoinColumn(name="id")
    @Column(name="id", nullable = false)    // 나중에 뺼것
    private String id; // 로그인 ID // 작성자

    @Column(name="reCDate")
    @CreatedDate
    private String reCDate; // 댓글 생성 날짜     // local

    @Column(name = "reUDate")
    @LastModifiedDate
    private String reUDate;    // 댓글 수정 날짜

    @Column(name="reContent")
    private String reContent; // 댓글 내용
}
