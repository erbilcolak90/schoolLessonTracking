package com.schoolplantracking.backend.businessService.managers;

import com.schoolplantracking.backend.businessService.StudentService;
import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Student;
import com.schoolplantracking.backend.repositories.StudentRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class StudentManager implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentManager(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //POST METHODS
    @Override
    public Result createStudent(Student student) {
        try {
            student.setCreateDate(new Date());
            student.setUpdateDate(new Date());
            student.setDeleted(false);
            this.studentRepository.save(student);

            return new Result<>(true, "Student created. Info :", student);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    //GET METHODS
    @Override
    public Result getStudentById(String studentId) {
        try {
            Student student = this.studentRepository.findById(studentId).orElseThrow();

            return new Result<>(true, "Student info : ", student);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Student>> getAllStudents() {
        try {
            List<Student> studentList = this.studentRepository.getAllStudents();

            return new Result<List<Student>>(true, "Student List : ", studentList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //PUT METHODS

    @Override
    public Result updateStudent(Student student) {
        try {
            Student tempStudent = this.studentRepository.findById(student.getId()).orElseThrow();
            tempStudent.setUpdateDate(new Date());
            tempStudent.setFirstName(student.getFirstName());
            tempStudent.setLastName(student.getLastName());
            tempStudent.setProfilePicture(student.getProfilePicture());

            this.studentRepository.save(tempStudent);
            return new Result<>(true, "Student is updated", tempStudent);

        } catch (Exception ex) {
        }
        return null;
    }


    //DELETE METHODS

    @Override
    public Result deleteStudent(String studentId) {
        try {
            Student student = this.studentRepository.findById(studentId).orElseThrow();
            student.setDeleted(true);
            student.setUpdateDate(new Date());

            this.studentRepository.save(student);
            return new Result<>(true, "Student is deleted", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
