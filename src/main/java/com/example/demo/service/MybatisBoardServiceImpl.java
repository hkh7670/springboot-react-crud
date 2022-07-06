package com.example.demo.service;

import com.example.demo.dto.BoardDtoCamel;
import com.example.demo.dto.BoardDtoUnderScore;
import com.example.demo.mapper.MybatisBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisBoardServiceImpl implements MybatisBoardService {

    private final MybatisBoardMapper mybatisBoardMapper;

    @Override
    public List<BoardDtoUnderScore> getPostAllUnderScore() {
        return mybatisBoardMapper.getPostAllUnderScore();
    }

    @Override
    public List<BoardDtoCamel> getPostAllCamel() {
        return mybatisBoardMapper.getPostAllCamel();
    }
}
