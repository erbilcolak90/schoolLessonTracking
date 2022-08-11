package com.schoolLessonTracking.repositories;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.TeacherFavoriteStudents;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherFavoriteStudentsRepository extends MongoRepository<TeacherFavoriteStudents,String> {

    TeacherFavoriteStudents findByTeacherId(String teacherId);

}
