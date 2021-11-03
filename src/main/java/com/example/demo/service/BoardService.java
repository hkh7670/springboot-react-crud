package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageBaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Page<BoardDto> getPostList(BoardDto boardDto, Pageable pageable);
    PageImpl<BoardDto> getPostListQueryDsl(BoardDto boardDto, Pageable pageable);
    PageBaseResponse getPostListQueryDsl(BoardDto boardDto);
    BoardDto getPostOne(Long seq);
    BoardDto insertPost(BoardDto dto);
    Long updatePost(BoardDto dto, Long id);
    void deletePost(Long seq);
    List<BoardDto> getPostByTitle(BoardDto dto);
}
