package com.example.demo.controller;

import com.example.demo.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestController {

    @PostMapping("/{param}")
    public ResponseEntity<?> test(@RequestBody List<Long> numbers, @PathVariable String param) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("numbers", numbers);
        response.put("param", param);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> postTest(TestDto testDto) {
        return ResponseEntity.ok(testDto);
    }
}
