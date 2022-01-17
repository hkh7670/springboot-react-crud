package com.example.demo.config;

import com.example.demo.dto.Area;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "area.info")
@Getter
@Setter
public class AreaList {

    private List<Area> list;

    public Area getAreaByAreaCode(String areaCode) {
        return list.stream().filter(item -> item.getCd().equals(areaCode)).findFirst().orElseThrow(NullPointerException::new);
    }
}
