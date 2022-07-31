package com.schoolplantracking.backend.controller;

import com.schoolplantracking.backend.businessService.StudentService;
import com.schoolplantracking.backend.businessService.TeacherService;
import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;
    private StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    //POST METHODS

    @PostMapping("/createTeacher")
    public Result createTeacher(@RequestBody Teacher teacher){
        return this.teacherService.createTeacher(teacher);
    }

    // GET METHODS
    @GetMapping("/getTeacher/{teacherId}")
    public Result getTeacher(@PathVariable("teacherId") String teacherId){
        return this.teacherService.getTeacher(teacherId);
    }

    @GetMapping("/getAllTeachers")
    public Result<List<Teacher>> getAllTeachers(){
        return this.teacherService.getAllTeachers();
    }

    //public Result<Page<Teacher>> pageableTeacher(int pageNumber, int pageSize) {}

        // PUT METHODS
    @PutMapping("/updateResponsibleStudent")
    public Result updateResponsibleStudentId(@RequestParam String teacherId,@RequestParam String studentId){
        return this.teacherService.updateResponsibleStudentId(teacherId, studentId);
    }

    @PutMapping("/updateTeacher")
    public Result updateTeacher(@Valid @RequestBody Teacher teacher){
        return this.teacherService.updateTeacher(teacher);
    }

    // DELETE METHODS
    @DeleteMapping("/deleteTeacher/{teacherId}")
    public Result deleteTeacher(@PathVariable("teacherId") String teacherId){
        return this.teacherService.deleteTeacher(teacherId);
    }

}
