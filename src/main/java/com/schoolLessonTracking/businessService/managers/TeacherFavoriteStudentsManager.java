package com.schoolLessonTracking.businessService.managers;

import com.schoolLessonTracking.businessService.StudentService;
import com.schoolLessonTracking.businessService.TeacherFavoriteStudentsService;
import com.schoolLessonTracking.businessService.TeacherService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.FavoriteStudent;
import com.schoolLessonTracking.entities.Teacher;
import com.schoolLessonTracking.entities.TeacherFavoriteStudents;
import com.schoolLessonTracking.repositories.StudentRepository;
import com.schoolLessonTracking.repositories.TeacherFavoriteStudentsRepository;
import com.schoolLessonTracking.repositories.TeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class TeacherFavoriteStudentsManager implements TeacherFavoriteStudentsService {

    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    private TeacherFavoriteStudentsRepository teacherFavoriteStudentsRepository;

    public TeacherFavoriteStudentsManager(TeacherRepository teacherRepository, StudentRepository studentRepository, TeacherFavoriteStudentsRepository teacherFavoriteStudentsRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.teacherFavoriteStudentsRepository = teacherFavoriteStudentsRepository;
    }

    @Override
    public Result<List<FavoriteStudent>> getAllTeacherFavoriteStudents(String teacherId) {
        try {
            Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();

            TeacherFavoriteStudents teacherFavoriteStudents = this.teacherFavoriteStudentsRepository.findByTeacherId(teacherId);

            return new Result<List<FavoriteStudent>>(true, "Favorite Students list ", teacherFavoriteStudents.getFavoriteStudentList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return null;
    }

    @Override
    public Result findByTeacherId(String teacherId) {
        try {
            return new Result<>(true, "Teacher find", this.teacherFavoriteStudentsRepository.findByTeacherId(teacherId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result createTeacherFavoriteStudents(TeacherFavoriteStudents teacherFavoriteStudents) {
        try {
            this.teacherFavoriteStudentsRepository.save(teacherFavoriteStudents);

            return new Result<>(true,"Teacher favorite students created",teacherFavoriteStudents);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
