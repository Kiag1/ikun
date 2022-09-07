package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.SimpleTimeZone;
import java.util.Vector;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("newstudent")
public class NewStudent<T> {
    private List<T> s;
    private double totaltime; //总时长
    private String starttime; //开始时间
    private String endtime; //结束时间
}
