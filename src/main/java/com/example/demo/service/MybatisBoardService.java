package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.BoardDtoCamel;
import com.example.demo.dto.BoardDtoUnderScore;
import com.example.demo.dto.PageBaseResponse;
import com.example.demo.entity.BoardCommentEntity;
import com.example.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MybatisBoardService {
    List<BoardDtoUnderScore> getPostAllUnderScore();
    List<BoardDtoCamel> getPostAllCamel();
}
