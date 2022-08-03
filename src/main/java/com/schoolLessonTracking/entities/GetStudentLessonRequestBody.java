package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetStudentLessonRequestBody {

    private String studentId;
    private Date from;
    private Date to;

}
