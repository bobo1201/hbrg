package com.hbrg.dto;

import com.hbrg.entity.Hbrg_Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hbrg_BoardDto {
    private Long boardId;
    private String id;
    private String title;
    private Long vC;
    private Long bLike;
    private LocalDateTime cDate;
    private LocalDateTime uDate;
    private String txt;

    public Hbrg_Board toEntity() {
        Hbrg_Board build = Hbrg_Board.builder()
            .id(id)
            .title(title)
            .txt(txt)
            .build();
        return build;
    }

    @Builder
    public Hbrg_BoardDto(String id, String title, String txt, LocalDateTime cDate, LocalDateTime uDate) {
        this.id = id;
        this.title = title;
        this.txt = txt;
        this.cDate = cDate;
        this.uDate = uDate;
    }
}
