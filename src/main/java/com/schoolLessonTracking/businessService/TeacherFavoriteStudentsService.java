package com.schoolLessonTracking.businessService;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.FavoriteStudent;
import com.schoolLessonTracking.entities.TeacherFavoriteStudents;

import java.util.List;

public interface TeacherFavoriteStudentsService {

    Result createTeacherFavoriteStudents(TeacherFavoriteStudents teacherFavoriteStudents);
    Result<List<FavoriteStudent>> getAllTeacherFavoriteStudents(String teacherId);

    Result findByTeacherId(String teacherId);
}
