package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("teacherFavoriteStudents")
public class TeacherFavoriteStudents {

    @Id
    private String id;
    private String teacherId;
    private List<FavoriteStudent> favoriteStudentList = new ArrayList<>();
}
