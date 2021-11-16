package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.PageBaseResponse;
import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

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
    public PageBaseResponse getPostListQueryDsl(BoardDto boardDto) {
        PageBaseResponse response = new PageBaseResponse();
        List<BoardDto> dbResponse = boardRepository.findByTitleByQueryDsl2(boardDto);
        response.getData().put("contents", dbResponse);
        Map<String, Integer> pagination = new HashMap<>();
        pagination.put("page", boardDto.getPage());
        pagination.put("totalCount", dbResponse.size());
        response.getData().put("pagination", pagination);
        return response;
    }

    @Override
    public BoardDto getPostOne(Long seq) {
        BoardEntity entity = boardRepository.findById(seq).orElseThrow(NullPointerException::new);
        return new BoardDto(entity);
    }

    @Override
    @Transactional
    public BoardDto insertPost(BoardDto dto) {
        return new BoardDto(boardRepository.save(dto.toEntity()));
    }

    @Override
    @Transactional
    public Long updatePost(BoardDto dto, Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return boardRepository.save(entity).getId();
    }

    @Override
    @Transactional
    public void deletePost(Long seq) {
        BoardEntity entity = boardRepository.findById(seq).orElseThrow(NullPointerException::new);
        boardRepository.delete(entity);
    }

    @Override
    public List<BoardDto> getPostByTitle(BoardDto dto) {
        return boardRepository.findTop3ByTitle(dto.getTitle()).stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }
}
