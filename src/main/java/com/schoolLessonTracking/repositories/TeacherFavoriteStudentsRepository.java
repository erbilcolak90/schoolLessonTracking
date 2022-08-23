package com.schoolLessonTracking.repositories;


import com.schoolLessonTracking.entities.TeacherFavoriteStudents;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeacherFavoriteStudentsRepository extends MongoRepository<TeacherFavoriteStudents,String> {

    TeacherFavoriteStudents findByTeacherId(String teacherId);

}
