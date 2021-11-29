package com.example.demo.service;

import com.example.demo.dto.BoardCommentDto;

import java.util.List;

public interface BoardCommentService {
    BoardCommentDto insertComment(BoardCommentDto request);

    List<BoardCommentDto> getChildCommentList(Long seq);
}
