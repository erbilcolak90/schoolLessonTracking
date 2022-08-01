package com.schoolLessonTracking.repositories;

import com.schoolLessonTracking.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {


    @Query(value = "{'isDeleted' : false}",sort = "{firstName : 1}")
    List<Student> getAllStudents();

}
