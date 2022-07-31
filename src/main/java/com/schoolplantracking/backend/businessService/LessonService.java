package com.schoolplantracking.backend.businessService;

import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Lesson;
import com.schoolplantracking.backend.entities.Teacher;

import java.util.Date;
import java.util.List;

public interface LessonService {

    Result createLesson(Lesson lesson);

    Result getLessonById(String lessonId);

    Result<List<Lesson>> getAllLessons();

    Result<List<Lesson>> findByLessonDateBetween(Date from, Date to);

    Result<List<Lesson>> findByStudentLesson(Date from,Date to,String studentId);

    Result<List<Lesson>> findByTeacherLesson(Date from,Date to,String teacherId);

    Result updateLesson(Lesson lesson);

    Result deleteLesson(String lessonId);
}
