package com.example.demo.dto;

import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentDto {
    private Long id;
    private Long parentCommentId;
    private Long postId;
    private Long userId;
    private String content;
    private String regDate;
    private String uptDate;
    private Long childCommentCnt;

    @Builder
    public BoardCommentDto(BoardCommentEntity entity, Long childCommentCnt) {
        this.id = entity.getId();
        this.parentCommentId = entity.getParentCommentId();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate() != null ? entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = entity.getUptDate() != null ? entity.getUptDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.childCommentCnt = childCommentCnt;
    }

    public BoardCommentDto(BoardCommentEntity entity) {
        this.id = entity.getId();
        this.parentCommentId = entity.getParentCommentId();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate() != null ? entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = entity.getUptDate() != null ? entity.getUptDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }

    public BoardCommentEntity toEntity() {
        return BoardCommentEntity.builder()
                .parentCommentId(parentCommentId)
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();
    }
}
