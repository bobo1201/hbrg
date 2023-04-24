package com.hbrg.entity;

import com.hbrg.dto.ReReplyFormDto;
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
public class ReReply  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reReId; // 대댓글 번호

    @ManyToOne
    @JoinColumn(name="reId")
    private Reply reply; // 댓글 번호

    private String id; // 로그인 ID

    private LocalDateTime reReCDate; // 대댓글 생성 날짜

    private String reReContent; // 대댓글 내용

    public static ReReply createReReply(ReReplyFormDto reReplyFormDto){
        ReReply reReply = new ReReply();
        reReply.setReReContent(reReplyFormDto.getReReContent());
        reReply.setReply(reReplyFormDto.getReply());
        return reReply;
    }
}
