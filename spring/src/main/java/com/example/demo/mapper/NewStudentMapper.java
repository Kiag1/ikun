package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.NewStudent;
import com.example.demo.entity.Nstudent;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
@Mapper
public interface NewStudentMapper extends BaseMapper<Nstudent> {
}
