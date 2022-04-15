package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestVo {
    private Integer code;
    private boolean success;
    private String  message;
    private Object  data;
}
