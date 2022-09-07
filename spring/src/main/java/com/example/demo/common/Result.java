package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//存放返回的数据泛型
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Boolean Success;
    private String msg;
    T data;
}
