package com.example.demo.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long parentCommentId;

    @Column
    private Long depth;

    @Column(name = "POST_ID")
    private Long postId;

    @Column
    private Long userId;

    @Column
    private String content;

    @Builder
    public BoardCommentEntity(Long id, Long parentCommentId, Long depth, Long postId, Long userId, String content) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.depth = depth;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}
