package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 조회
    @GetMapping
    public ResponseEntity<?> getPostList(BoardDto boardDto, Pageable pageable) {
        return ResponseEntity.ok(boardService.getPostList(boardDto, pageable));
    }

    @GetMapping("/{seq}")
    public ResponseEntity<?> getPostOne(@PathVariable Long seq) {
        return ResponseEntity.ok(boardService.getPostOne(seq));
    }

    @PostMapping
    public ResponseEntity<?> insertPost(BoardDto dto) {
        return ResponseEntity.ok(boardService.insertPost(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(BoardDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(boardService.updatePost(dto, id));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<?> deletePost(@PathVariable Long seq) {
        boardService.deletePost(seq);
        return ResponseEntity.ok("");
    }

    @GetMapping("/queryDsl")
    public ResponseEntity<?> getPostListQueryDsl(BoardDto boardDto, Pageable pageable) {
        return ResponseEntity.ok(boardService.getPostListQueryDsl(boardDto, pageable));
    }


    @GetMapping("/title")
    public ResponseEntity<?> getPostByTitle(BoardDto dto) {
        return ResponseEntity.ok(boardService.getPostByTitle(dto));
    }
}
