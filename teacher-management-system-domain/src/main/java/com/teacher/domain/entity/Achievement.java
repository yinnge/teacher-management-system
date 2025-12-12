package com.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.teacher.domain.handler.FloatArrayTypeHandler;
import lombok.Data;

@Data
@TableName("t_achievement")
public class Achievement {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;
    private String description;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;

    @TableField(typeHandler = FloatArrayTypeHandler.class)
    private float[] vector;

}

