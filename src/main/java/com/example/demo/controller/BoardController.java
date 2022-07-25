package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 조회
    @GetMapping
    public ResponseEntity<Page<BoardDto>> getPostList(BoardDto boardDto, Pageable pageable) {
        return ResponseEntity.ok(boardService.getPostList(boardDto, pageable));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<BoardDto> getPostOne(@PathVariable Long seq) {
        return ResponseEntity.ok(boardService.getPostOne(seq));
    }

    @PostMapping
    public ResponseEntity<BoardDto> insertPost(BoardDto dto) {
        return ResponseEntity.ok(boardService.insertPost(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePost(BoardDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(boardService.updatePost(dto, id));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<?> deletePost(@PathVariable Long seq) {
        boardService.deletePost(seq);
        return ResponseEntity.ok("");
    }

    @GetMapping("/queryDsl")
    public ResponseEntity<PageImpl<BoardDto>> getPostListQueryDsl(BoardDto boardDto, Pageable pageable) {
        return ResponseEntity.ok(boardService.getPostListQueryDsl(boardDto, pageable));
    }


    @GetMapping("/title")
    public ResponseEntity<List<BoardDto>> getPostByTitle(BoardDto dto) {
        return ResponseEntity.ok(boardService.getPostByTitle(dto));
    }

    @GetMapping("/test")
    public ResponseEntity<List<BoardEntity>> transactionTest() {
        boardService.setPostList();
        return ResponseEntity.ok(boardService.getPostAll());
    }

    @GetMapping("/test/comment")
    public ResponseEntity<List<BoardCommentEntity>> manyToOneTest() {
        return ResponseEntity.ok(boardService.getCommentList());
    }
}
