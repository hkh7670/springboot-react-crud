package com.example.demo.dto;

import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentDto {
    private Long id;
    private Long parentCommentId;
    private Long depth;
    private Long postId;
    private Long userId;
    private String content;
    private String regDate;
    private String uptDate;

    @Builder
    public BoardCommentDto(Long id, Long parentCommentId, Long depth, Long postId, Long userId, String content, LocalDateTime regDate, LocalDateTime uptDate) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.depth = depth;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.regDate = regDate != null ? regDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = uptDate != null ? uptDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }

    public BoardCommentDto(BoardCommentEntity entity) {
        this.id = entity.getId();
        this.parentCommentId = entity.getParentCommentId();
        this.depth = entity.getDepth();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate() != null ? entity.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = entity.getUptDate() != null ? entity.getUptDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }

    public BoardCommentEntity toEntity() {
        return BoardCommentEntity.builder()
                .parentCommentId(parentCommentId)
                .depth(depth)
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();
    }
}
