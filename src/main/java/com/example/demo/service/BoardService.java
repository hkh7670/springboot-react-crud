package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageBaseResponse;
import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Page<BoardDto> getPostList(BoardDto boardDto, Pageable pageable);
    PageImpl<BoardDto> getPostListQueryDsl(BoardDto boardDto, Pageable pageable);
    BoardDto getPostOne(Long seq);
    BoardDto insertPost(BoardDto dto);
    Long updatePost(BoardDto dto, Long id);
    void deletePost(Long seq);
    List<BoardDto> getPostByTitle(BoardDto dto);
    void setPostList();
    List<BoardEntity> getPostAll();
    List<BoardCommentEntity> getCommentList();
}
