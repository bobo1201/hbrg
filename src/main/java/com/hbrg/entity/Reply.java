package com.hbrg.entity;

import com.hbrg.dto.ReplyFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Reply")
@Getter
@Setter
@ToString
public class Reply extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reId; // 댓글 번호

    @ManyToOne
    @JoinColumn(name="boardId")
    private Board board; // 게시물 번호

    @Column()
    private String reContent; // 댓글 내용

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReReply> reReplies = new ArrayList<>();

    public static Reply createReply(ReplyFormDto replyFormDto){
        Reply reply = new Reply();
        reply.setReContent(replyFormDto.getReContent());
        reply.setBoard(replyFormDto.getBoard());
        return reply;
    }

}