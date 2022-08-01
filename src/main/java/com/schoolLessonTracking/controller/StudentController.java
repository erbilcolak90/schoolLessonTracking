package com.schoolLessonTracking.controller;

import com.schoolLessonTracking.businessService.StudentService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createStudent")
    @CrossOrigin(origins = "*")
    public Result createStudent(@Valid @RequestBody Student student){
        return this.studentService.createStudent(student);
    }

    @GetMapping("/getStudentById/{studentId}")
    @CrossOrigin(origins = "*")
    public Result getStudentById(@PathVariable("studentId") String studentId){
        return this.studentService.getStudentById(studentId);
    }

    @GetMapping("/getAllStudents")
    @CrossOrigin(origins = "*")
    public Result<List<Student>> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @PutMapping("/updateStudent")
    @CrossOrigin(origins = "*")
    public Result updateStudent(@Valid @RequestBody Student student){
        return this.studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent")
    @CrossOrigin(origins = "*")
    public Result deleteStudent(@RequestParam String studentId){
        return this.studentService.deleteStudent(studentId);
    }


}
