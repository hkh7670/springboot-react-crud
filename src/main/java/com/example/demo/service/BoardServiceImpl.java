package com.example.demo.service;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageBaseResponse;
import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.BoardCommentRepository;
import com.example.demo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Override
    public Page<BoardDto> getPostList(BoardDto boardDto, Pageable pageable) {
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("uptDate"));
        // return boardRepository.postPageResponse(pageable);
        return boardRepository.getBoardList(boardDto, pageable);
    }

    @Override
    public PageImpl<BoardDto> getPostListQueryDsl(BoardDto boardDto, Pageable pageable) {
        return boardRepository.findByTitleByQueryDsl(boardDto, pageable);
    }

    @Override
    public BoardDto getPostOne(Long seq) {
        BoardEntity entity = boardRepository.findById(seq).orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "boardSeq", seq.toString()));
        //return new BoardDto(entity, boardCommentRepository.findBoardCommentList(seq));
        return BoardDto.builder()
                .entity(entity)
                .boardCommentList(boardCommentRepository.findBoardCommentList(seq))
                .build();
    }

    @Override
    @Transactional
    public BoardDto insertPost(BoardDto dto) {
        return new BoardDto(boardRepository.save(dto.toEntity()));
    }

    @Override
    @Transactional
    public Long updatePost(BoardDto dto, Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "boardSeq", id.toString()));
        entity.updatePost(dto);
        return boardRepository.save(entity).getId();
    }

    @Override
    @Transactional
    public void deletePost(Long seq) {
        BoardEntity entity = boardRepository.findById(seq).orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND, "boardSeq", seq.toString()));
        boardRepository.delete(entity);
    }

    @Override
    public List<BoardDto> getPostByTitle(BoardDto dto) {
        return boardRepository.findTop3ByTitle(dto.getTitle()).stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setPostList() {
        List<BoardEntity> boardEntityList = boardRepository.findByIdIn(Arrays.asList(95L));
        log.info(" >>> boardEntityListSize : " + boardEntityList.size());
        for (BoardEntity boardEntity : boardEntityList) {
            List<BoardCommentEntity> boardCommentEntityList = boardEntity.getCommentList();
            log.info(" >>> boardCommentEntityListSize : " + boardCommentEntityList.size());
            for (BoardCommentEntity boardCommentEntity : boardCommentEntityList) {
                boardCommentEntity.setContent("comment seq : " + boardCommentEntity.getId() + " 테스트");
                /*if (boardCommentEntity.getParentCommentId() != null) {

                }*/
            }
        }
        boardRepository.saveAll(boardEntityList);
    }

    @Override
    public List<BoardEntity> getPostAll() {
        List<BoardEntity> boardEntityList = boardRepository.findByIdIn(Arrays.asList(95L));
        return boardEntityList;
    }

    @Override
    public List<BoardCommentEntity> getCommentList() {
        List<BoardCommentEntity> boardCommentEntityList = boardCommentRepository.findByPostId(95L);
        return boardCommentEntityList;
    }
}
