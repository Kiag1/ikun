package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.net.httpserver.Authenticator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
//    @TableId(type = IdType.AUTO) //字段自增
    private String name; //姓名
    private String department; //学院
    private String id; //学号
    private int stime;  //时长
    private String tt;  //日期
}

