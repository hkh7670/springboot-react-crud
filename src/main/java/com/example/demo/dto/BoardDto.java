package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardDto extends PagingRequest {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String regDate;
    private String uptDate;
    private List<BoardCommentDto> commentList;

    public BoardDto() {

    }

    public BoardDto(BoardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.commentList = entity.getCommentList().stream().map(BoardCommentDto::new).collect(Collectors.toList());
        this.regDate = entity.getRegDate() != null ? entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = entity.getUptDate() != null ? entity.getUptDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }

    public BoardDto(Long id, String title, String content, String userId, LocalDateTime regDate, LocalDateTime uptDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDate = regDate != null ? regDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        ;
        this.uptDate = uptDate != null ? uptDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        ;
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
