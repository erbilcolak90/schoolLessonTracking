package com.schoolLessonTracking.businessService;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {

    Result createTeacher(Teacher teacher);

    Result getTeacher(String teacherId);

    Result<List<Teacher>> getAllTeachers();

    Result updateResponsibleStudentId(String teacherId,String studenId);

    Result updateTeacher(Teacher teacher);


    Result deleteTeacher(String teacherId);

}
