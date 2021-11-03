package com.example.demo.entity;

import com.example.demo.dto.BoardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "BOARD")
@NoArgsConstructor
public class BoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String userId;

    @Builder
    public BoardEntity(Long id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public BoardDto toDto() {
        return BoardDto.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .content(content)
                .regDate(getRegDate())
                .uptDate(getUptDate())
                .build();
    }
}
