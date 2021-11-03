package com.example.demo.dto;

import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
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
