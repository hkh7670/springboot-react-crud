package com.example.demo.repository;

import com.example.demo.dto.BoardDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    PageImpl<BoardDto> getBoardList(BoardDto boardDto, Pageable pageable);
    PageImpl<BoardDto> findByTitleByQueryDsl(BoardDto boardDto, Pageable pageable);
}
