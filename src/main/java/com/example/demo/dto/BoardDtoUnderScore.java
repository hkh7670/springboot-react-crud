package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import com.example.demo.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardDtoUnderScore {
    private Long id;
    private String title;
    private String content;
    private String user_Id;
    private String reg_Date;
    private String upt_Date;
}
