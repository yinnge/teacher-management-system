package com.teacher.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_embedding")
public class Embedding {
    @TableId
    private Long id;
    private String sourceType;
    private Long sourceId;
    private String content;
    private String embedding;
}
