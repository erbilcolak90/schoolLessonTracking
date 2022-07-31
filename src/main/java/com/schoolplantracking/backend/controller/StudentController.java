package com.schoolplantracking.backend.controller;

import com.schoolplantracking.backend.businessService.StudentService;
import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Student;
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
    public Result createStudent(@Valid @RequestBody Student student){
        return this.studentService.createStudent(student);
    }

    @GetMapping("/getStudentById/{studentId}")
    public Result getStudentById(@PathVariable("studentId") String studentId){
        return this.studentService.getStudentById(studentId);
    }

    @GetMapping("/getAllStudents")
    public Result<List<Student>> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @PutMapping("/updateStudent")
    public Result updateStudent(@Valid @RequestBody Student student){
        return this.studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent")
    public Result deleteStudent(@RequestParam String studentId){
        return this.studentService.deleteStudent(studentId);
    }


}
