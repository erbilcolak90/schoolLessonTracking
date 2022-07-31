package com.schoolplantracking.backend.repositories;

import com.schoolplantracking.backend.entities.Student;
import com.schoolplantracking.backend.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {


    @Query(value = "{'isDeleted' : false}",sort = "{firstName : 1}")
    List<Student> getAllStudents();

}
