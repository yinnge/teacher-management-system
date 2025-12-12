package com.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("t_course")
public class Course {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @TableField("teacher_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;
    private String description;
}
