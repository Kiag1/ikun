package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private StudentMapper studentMapper;
	@Test
	void contextLoads() {
		Student student = new Student("黄连东", "8", "2007310308", 59, "2021-10-24");
		QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", student.getId()).last("limit 1"); //判断是否数据库中是否存在该数据
		Long i = studentMapper.selectCount(queryWrapper);
		Result<Student> results = null;
		if (i != 1) {
			System.out.println(new Result<>(false, null, student));
		} else {
			studentMapper.updateById(student);
			String msg = student.getId();
			if (msg != null) {
				results = new Result<>(true, msg, student);
			}
		}
		System.out.println(new Result<>(false, null, student));
	}
}
