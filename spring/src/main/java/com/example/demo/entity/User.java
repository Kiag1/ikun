package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String nickname;
}
