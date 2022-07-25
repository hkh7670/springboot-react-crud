package com.example.demo.dto;

import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.regDate = StringUtil.localDateTimeToString(entity.getRegDate());
        this.uptDate = StringUtil.localDateTimeToString(entity.getUptDate());
        this.childCommentCnt = childCommentCnt;
    }

    public BoardCommentDto(BoardCommentEntity entity) {
        this.id = entity.getId();
        this.parentCommentId = entity.getParentCommentId();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.regDate = StringUtil.localDateTimeToString(entity.getRegDate());
        this.uptDate = StringUtil.localDateTimeToString(entity.getUptDate());
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
