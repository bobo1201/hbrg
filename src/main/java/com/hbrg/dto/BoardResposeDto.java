//package com.hbrg.dto;
//
//import com.hbrg.entity.Board;
//import com.hbrg.entity.Hfile;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//public class BoardResposeDto {
//
//    private Long boardId;
//
//    private String title;
//
//    private String content;
//
//    private String createdDate, modifiedDate;
//
//    @ColumnDefault("0")
//    private int vc;
//
//    private Hfile file;
//
////    private Huser user;
//
//    private List<ReplyResponseDto> reply;
//
//    /* Entity -> Dto*/
//    public BoardResposeDto(Board board) {
//        this.boardId = board.getBoardId();
//        this.title = board.getTitle();
//        this.content = board.getContent();
//        this.vc = board.getVc();
//        this.reply = board.getReplies().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
//    }
//
//}
