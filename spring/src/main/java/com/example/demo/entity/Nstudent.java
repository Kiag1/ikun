package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("newstudent")
public class Nstudent {
    private String name; //姓名
    private String department; //学院
    private String id; //学号
    private int stime;  //时长
    private String tt;  //日期
}
