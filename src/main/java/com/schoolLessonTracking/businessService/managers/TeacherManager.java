package com.schoolLessonTracking.businessService.managers;

import com.schoolLessonTracking.businessService.TeacherService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Student;
import com.schoolLessonTracking.entities.Teacher;
import com.schoolLessonTracking.repositories.StudentRepository;
import com.schoolLessonTracking.repositories.TeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class TeacherManager implements TeacherService {

    private TeacherRepository teacherRepository;

    private StudentRepository studentRepository;

    @Autowired
    public TeacherManager(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    // POST METHODS
    @Override
    public Result createTeacher(Teacher teacher) {
        try {
            teacher.setCreateDate(new Date());
            teacher.setUpdateDate(new Date());
            teacher.setResponsibleStudentIds(null);
            teacher.setDeleted(false);
            this.teacherRepository.save(teacher);

            return new Result(true, "teacher is a created", teacher);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // GET METHODS
    @Override
    public Result getTeacher(String teacherId) {
        try {
            Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();
            return new Result<>(true, "teacher info : ", teacher);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Teacher>> getAllTeachers() {
        try {
            List<Teacher> teacherList = this.teacherRepository.getAllTeachers();

            return new Result<List<Teacher>>(true, "Teacher size : " + teacherList.size() + "", teacherList);
        } catch (Exception ex) {
        }
        return null;
    }


    // PUT METHODS

    //Eğer öğrenci ID öğretmenin sorumluluk listesindeyse öğrenci no listeden silinir ve güncellenir.
    //Değilse öğrenci öğretmenin sorumluluk listesine eklenir.
    @Override
    public Result updateResponsibleStudentId(String teacherId, String studentId) {
        try {
            Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();
            Student student = this.studentRepository.findById(studentId).orElseThrow();

            if (teacher.getResponsibleStudentIds().contains(studentId)) {
                teacher.getResponsibleStudentIds().remove(studentId);
                teacher.setUpdateDate(new Date());

                this.teacherRepository.save(teacher);
                return new Result<>(true, "Student is removed from teacher's responsibility, updated responsibility list : ", teacher.getResponsibleStudentIds());
            } else {
                teacher.getResponsibleStudentIds().add(studentId);
                teacher.setUpdateDate(new Date());
                this.teacherRepository.save(teacher);
                return new Result<>(true, "Student is added to teacher's responsibility, updated responsibility list :", teacher.getResponsibleStudentIds());
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result updateTeacher(Teacher teacher) {
        try {
            Teacher tempTeacher = this.teacherRepository.findById(teacher.getId()).orElseThrow();
            tempTeacher.setUpdateDate(new Date());
            tempTeacher.setFirstName(teacher.getFirstName());
            tempTeacher.setLastName(teacher.getLastName());
            tempTeacher.setProfession(teacher.getProfession());
            tempTeacher.setProfilePicture(teacher.getProfilePicture());

            this.teacherRepository.save(tempTeacher);
            return new Result<>(true, "Teacher is updated", tempTeacher);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // DELETE METHODS

    @Override
    public Result deleteTeacher(String teacherId) {
        try {
            Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();
            teacher.setDeleted(true);
            teacher.setUpdateDate(new Date());

            this.teacherRepository.save(teacher);

            return new Result<>(true, "teacher deleted", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
