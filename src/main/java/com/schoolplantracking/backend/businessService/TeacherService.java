package com.schoolplantracking.backend.businessService;

import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {

    Result createTeacher(Teacher teacher);

    Result getTeacher(String teacherId);

    Result<List<Teacher>> getAllTeachers();

    Result<Page<Teacher>> pageableTeacher(int pageNumber,int pageSize);

    //Result<List<Teacher>> getAllTeachersStudentResponsibilite();

    //List<Teacher> SortByResponsibleStudentId();

    Result updateResponsibleStudentId(String teacherId,String studenId);

    Result updateTeacher(Teacher teacher);


    Result deleteTeacher(String teacherId);

}
