package com.example.demo.entity;

import com.example.demo.dto.BoardCommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "BOARD_COMMENT")
@NoArgsConstructor
public class BoardCommentEntity extends BaseTimeEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long parentCommentId;

    @Column(name = "POST_ID")
    private Long postId;

    @Column
    private Long userId;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
    private BoardEntity boardEntity;

    @Builder
    public BoardCommentEntity(Long id, Long parentCommentId, Long postId, Long userId, String content) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public void updateComment(BoardCommentDto boardCommentDto) {
        this.content = boardCommentDto.getContent();
    }
}
