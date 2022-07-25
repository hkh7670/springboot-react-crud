package com.example.demo.dto;

import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import com.example.demo.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String regDate;
    private String uptDate;
    private List<BoardCommentDto> commentList;

    public BoardDto() {

    }

    @Builder
    public BoardDto(BoardEntity entity, List<BoardCommentDto> boardCommentList) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.commentList = boardCommentList;
        this.regDate = StringUtil.localDateTimeToString(entity.getRegDate());
        this.uptDate = StringUtil.localDateTimeToString(entity.getUptDate());
    }

    public BoardDto(BoardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.commentList = entity.getCommentList().stream().map(BoardCommentDto::new).collect(Collectors.toList());
        this.regDate = StringUtil.localDateTimeToString(entity.getRegDate());
        this.uptDate = StringUtil.localDateTimeToString(entity.getUptDate());
    }

    public BoardDto(Long id, String title, String content, String userId, LocalDateTime regDate, LocalDateTime uptDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDate = StringUtil.localDateTimeToString(regDate);
        this.uptDate = StringUtil.localDateTimeToString(uptDate);
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
