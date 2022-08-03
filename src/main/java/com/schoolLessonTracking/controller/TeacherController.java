package com.schoolLessonTracking.controller;

import com.schoolLessonTracking.businessService.StudentService;
import com.schoolLessonTracking.businessService.TeacherService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
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
    @CrossOrigin(origins = "*")
    public Result createTeacher(@RequestBody Teacher teacher){
        return this.teacherService.createTeacher(teacher);
    }

    // GET METHODS
    @GetMapping("/getTeacher/{teacherId}")
    @CrossOrigin(origins = "*")
    public Result getTeacher(@PathVariable("teacherId") String teacherId){
        return this.teacherService.getTeacher(teacherId);
    }

    @GetMapping("/getAllTeachers")
    @CrossOrigin(origins = "*")
    public Result<List<Teacher>> getAllTeachers(){
        return this.teacherService.getAllTeachers();
    }

        // PUT METHODS
    @PutMapping("/updateResponsibleStudent")
    @CrossOrigin(origins = "*")
    public Result updateResponsibleStudentId(@RequestParam String teacherId,@RequestParam String studentId){
        return this.teacherService.updateResponsibleStudentId(teacherId, studentId);
    }

    @PutMapping("/updateTeacher")
    @CrossOrigin(origins = "*")
    public Result updateTeacher(@Valid @RequestBody Teacher teacher){
        return this.teacherService.updateTeacher(teacher);
    }

    // DELETE METHODS
    @DeleteMapping("/deleteTeacher/{teacherId}")
    @CrossOrigin(origins = "*")
    public Result deleteTeacher(@PathVariable("teacherId") String teacherId){
        return this.teacherService.deleteTeacher(teacherId);
    }

}
