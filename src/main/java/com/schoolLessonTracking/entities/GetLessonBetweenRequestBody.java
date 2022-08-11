package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLessonBetweenRequestBody {

    private Date from;
    private Date to;
}
