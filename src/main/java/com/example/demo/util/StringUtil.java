package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) : "-";
    }
}
