package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingRequest {
    private Integer page; // 현재 페이지
    private Integer perPage; // 한 페이지에 표현되는 로우의 개수
}
