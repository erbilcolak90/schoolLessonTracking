package com.schoolLessonTracking.businessService;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Student;

import java.util.List;

public interface StudentService {

    Result createStudent(Student student);

    Result getStudentById(String studentId);

    Result<List<Student>> getAllStudents();

    Result updateStudent(Student student);

    Result deleteStudent(String studentId);
}
