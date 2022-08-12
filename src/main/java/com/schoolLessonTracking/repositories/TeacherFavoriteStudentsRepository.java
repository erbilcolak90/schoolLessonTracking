package com.schoolLessonTracking.repositories;


import com.schoolLessonTracking.entities.TeacherFavoriteStudents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TeacherFavoriteStudentsRepository extends MongoRepository<TeacherFavoriteStudents,String> {

    @Query(value = "{'teacherId' : teacherId}",sort = "{favoriteStudentList : 1}")
    TeacherFavoriteStudents findByTeacherId(String teacherId);

}
