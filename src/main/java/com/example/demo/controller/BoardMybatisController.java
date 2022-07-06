package com.example.demo.controller;

import com.example.demo.dto.BoardDtoCamel;
import com.example.demo.dto.BoardDtoUnderScore;
import com.example.demo.service.MybatisBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/mybatis")
public class BoardMybatisController {

    private final MybatisBoardService mybatisBoardService;

    @GetMapping("/all/underScore")
    public ResponseEntity<List<BoardDtoUnderScore>> getPostAllUnderScore() {
        return ResponseEntity.ok(mybatisBoardService.getPostAllUnderScore());
    }

    @GetMapping("/all/camel")
    public ResponseEntity<List<BoardDtoCamel>> getPostAllCamel() {
        return ResponseEntity.ok(mybatisBoardService.getPostAllCamel());
    }
}
