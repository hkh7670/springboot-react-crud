package com.example.demo.service;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.BoardCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    @Override
    public BoardCommentDto insertComment(BoardCommentDto request) {
        return new BoardCommentDto(boardCommentRepository.save(request.toEntity()));
    }

    @Override
    public BoardCommentDto updateComment(BoardCommentDto request) {
        BoardCommentEntity boardCommentEntity = boardCommentRepository.findById(request.getId()).orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "comment id", request.getId().toString()));
        boardCommentEntity.updateComment(request);
        return new BoardCommentDto(boardCommentRepository.save(boardCommentEntity));
    }

    @Override
    public List<BoardCommentDto> getChildCommentList(Long seq) {
        List<BoardCommentEntity> response = boardCommentRepository.findByParentCommentIdOrderByRegDateDesc(seq);
        List<BoardCommentDto> result = new ArrayList<>();
        response.forEach(item -> {
            result.add(
                    BoardCommentDto.builder()
                        .entity(item)
                        .build()
            );
        });
        return result;
    }
}
