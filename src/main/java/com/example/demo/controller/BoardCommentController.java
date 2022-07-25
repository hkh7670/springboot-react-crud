package com.example.demo.controller;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.service.BoardCommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/comment")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping
    public ResponseEntity<?> insertComment(@RequestBody BoardCommentDto request) {
        return ResponseEntity.ok(boardCommentService.insertComment(request));
    }

    @PutMapping
    public ResponseEntity<?> updateComment(@RequestBody BoardCommentDto request) {
        return ResponseEntity.ok(boardCommentService.updateComment(request));
    }

    @GetMapping("/child/{seq}")
    public ResponseEntity<?> getChildCommentList(@PathVariable Long seq) {
        return ResponseEntity.ok(boardCommentService.getChildCommentList(seq));
    }
}
