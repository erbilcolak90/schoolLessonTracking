package com.schoolplantracking.backend.businessService;

import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Student;

import java.util.List;

public interface StudentService {

    Result createStudent(Student student);

    Result getStudentById(String studentId);

    Result<List<Student>> getAllStudents();

    Result updateStudent(Student student);

    Result deleteStudent(String studentId);
}
