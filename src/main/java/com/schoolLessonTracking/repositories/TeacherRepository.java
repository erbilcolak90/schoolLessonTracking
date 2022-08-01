package com.schoolLessonTracking.repositories;

import com.schoolLessonTracking.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher,String> {

        /*
    @Query(sort = "{'responsibleStudent.size' : -1}")
    List<Teacher> SortByResponsibleStudentId();
         */

    @Query(value = "{'isDeleted' : false}",sort = "{firstName : 1}")
    List<Teacher> getAllTeachers();

}
