package com.example.demo.mapper;

import com.example.demo.dto.BoardDtoCamel;
import com.example.demo.dto.BoardDtoUnderScore;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MybatisBoardMapper {

    List<BoardDtoUnderScore> getPostAllUnderScore();
    List<BoardDtoCamel> getPostAllCamel();
}
