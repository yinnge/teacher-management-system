package com.teacher.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseUpdateRequest {
    private String name;
    private String description;

    @JsonProperty("teacher_id")
    private Long teacherId;
}
