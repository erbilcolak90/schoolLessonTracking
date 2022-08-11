package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteStudent {

    private String studentId;
    private int lessonCount;
}
