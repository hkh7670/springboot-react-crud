package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class BoardDto extends PagingRequest {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String regDate;
    private String uptDate;

    @Builder
    public BoardDto(Long id, String title, String content, String userId, LocalDateTime regDate, LocalDateTime uptDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDate = regDate != null ? regDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
        this.uptDate = uptDate != null ? uptDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
