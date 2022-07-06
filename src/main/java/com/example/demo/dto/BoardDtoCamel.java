package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDtoCamel {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String regDate;
    private String uptDate;
}
