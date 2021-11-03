package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class PageBaseResponse {
    Boolean result = true;
    HashMap<String, Object> data = new HashMap<>();
}
