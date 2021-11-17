package com.example.demo.service;

import com.example.demo.dto.BoardCommentDto;

public interface BoardCommentService {
    BoardCommentDto insertComment(BoardCommentDto request);
}
