package com.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("t_teacher")
public class Teacher {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;  // 关联用户名
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String name;
    private String department;
}
