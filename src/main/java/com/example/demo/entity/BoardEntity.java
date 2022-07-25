package com.example.demo.entity;

import com.example.demo.dto.BoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor
public class BoardEntity extends BaseTimeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String userId;

    @OneToMany(mappedBy = "boardEntity", orphanRemoval = true)
    private List<BoardCommentEntity> commentList = new ArrayList<>();

    @Builder
    public BoardEntity(Long id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public void updatePost(BoardDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
