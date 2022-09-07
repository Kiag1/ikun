package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("newstudent")
public class NewPage<T> {
    private long totalPage;//总页数

    private long currentPage;//当前页数

    private long totalNum;//总数

    private List<T> contentList;//页数
}
