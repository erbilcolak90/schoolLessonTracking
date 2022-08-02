package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTeacherLessonRequestBody {

    private String teacherId;
    private Date from;
    private Date to;

}
