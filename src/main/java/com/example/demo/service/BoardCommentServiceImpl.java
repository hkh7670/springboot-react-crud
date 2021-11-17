package com.example.demo.service;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.repository.BoardCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    @Override
    public BoardCommentDto insertComment(BoardCommentDto request) {
        return new BoardCommentDto(boardCommentRepository.save(request.toEntity()));
    }
}
