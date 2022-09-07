package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.NewPage;
import com.example.demo.entity.NewStudent;
import com.example.demo.entity.Nstudent;
import com.example.demo.mapper.NewStudentMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*", "null"})//跨域
@RestController
public class NstudentController {
    @Autowired
    NewStudentMapper newStudentMapper;

    @PostMapping("/updates")
    public NewStudent<Nstudent> uptates(@RequestBody Nstudent nstudent) {
        QueryWrapper<Nstudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tt", nstudent.getTt());
        long i = newStudentMapper.selectCount(queryWrapper);
        if (i == 0) {
            return new NewStudent<>(null, -1, "error", "error");
        }
        newStudentMapper.update(nstudent, queryWrapper);
        return new NewStudent<>(null, -1, "true", "true");
    }

    @PostMapping("/deletes")
    public NewStudent<Nstudent> deletes(@RequestBody Nstudent nstudent) {
        QueryWrapper<Nstudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tt", nstudent.getTt());
        long i = newStudentMapper.selectCount(queryWrapper);

        if (i == 0) {
            return new NewStudent<>(null, -1, "error", "error");
        }
        newStudentMapper.delete(queryWrapper);
        return new NewStudent<>(null, -1, "true", "true");
    }

    @PostMapping("/adds")
    public NewStudent<Nstudent> adds(@RequestBody Nstudent nstudent) {
        QueryWrapper<Nstudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tt", nstudent.getTt()).last("limit 1");
        long i = newStudentMapper.selectCount(queryWrapper);
        if (i == 0) {
            newStudentMapper.insert(nstudent);
            return new NewStudent<>(null, -1, "true", "true");
        }
        return new NewStudent<>(null, -1, "error", "error");
    }

    @GetMapping("finds")
    public NewPage<Nstudent> finds(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "") String search) {
        NewPage<Nstudent> newPage = new NewPage<>();
        LambdaQueryWrapper<Nstudent> lambdaQueryWrapper = Wrappers.<Nstudent>lambdaQuery();
        Page<Nstudent> page = new Page<>(pageNum, pageSize);
        if (StrUtil.isNotBlank(search)) {
            lambdaQueryWrapper.like(Nstudent::getName, search);
        }
        Page<Nstudent> r = newStudentMapper.selectPage(page, lambdaQueryWrapper);
        newPage.setCurrentPage(r.getCurrent());
        newPage.setTotalNum(r.getTotal());
        newPage.setTotalPage(r.getPages());
        newPage.setContentList(r.getRecords());
        return newPage;
    }

    @GetMapping("/getmsg")
    public NewStudent<Nstudent> getmsg(@RequestParam(defaultValue = "") String search,
                                       @RequestParam(defaultValue = "") String id) {
        NewStudent<Nstudent> newStudent = new NewStudent<>();
        LambdaQueryWrapper<Nstudent> lambdaQueryWrapper = Wrappers.<Nstudent>lambdaQuery();

        if (StrUtil.isNotBlank(search)) {
            lambdaQueryWrapper.eq(Nstudent::getName, search).eq(Nstudent::getId, id);
            long i = newStudentMapper.selectCount(lambdaQueryWrapper);
            if (i == 0) {
                return new NewStudent<>(null, -1, "error", "error");
            }
        }
        newStudent.setS(newStudentMapper.selectList(lambdaQueryWrapper));

        int sum = 0;
        for (Nstudent n : newStudent.getS()) {
            sum += n.getStime();
        }
        newStudent.setTotaltime(sum);

        return newStudent;
    }

    @GetMapping("/getsum")
    public Temp<Nstudent> getsum(@RequestParam(defaultValue = "") String search,
                                 @RequestParam(defaultValue = "") String idt) {
        NewStudent<Nstudent> newStudent = new NewStudent<>();
        LambdaQueryWrapper<Nstudent> lambdaQueryWrapper = Wrappers.<Nstudent>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            lambdaQueryWrapper.eq(Nstudent::getName, search).eq(Nstudent::getId, idt);
            long i = newStudentMapper.selectCount(lambdaQueryWrapper);
            if (i == 0) {
                return new Temp(null, "noe", null, null, null, null, null);
            }
        }

        newStudent.setS(newStudentMapper.selectList(lambdaQueryWrapper));


        double sum = 0;
        int b1, e1;
        b1 = e1 = 0;
        String begin, end;
        begin = end = "";
        String name, de, id;
        name = de = id = "";

        int len = 1, last = newStudent.getS().size();
        NewStudent<Nstudent> temp = new NewStudent<>();
        for (Nstudent n : newStudent.getS()) {
            name = n.getName();
            de = n.getDepartment();
            id = n.getId();
            if (len == last) {
                end = n.getTt();
            }
            if (b1 == 0) {
                begin = n.getTt();
                b1++;
            }
            sum += n.getStime(); //总时长
            len++;
        }
        newStudent.setEndtime(end);
        newStudent.setStarttime(begin);
        newStudent.setTotaltime(sum);
        return new Temp(newStudent.getS(), name, de, id, begin, end, sum + "");
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Temp<T> {
    List<T> t;
    String name;
    String de;
    String sno;
    String begintime;
    String endtime;
    String totaltime;
}