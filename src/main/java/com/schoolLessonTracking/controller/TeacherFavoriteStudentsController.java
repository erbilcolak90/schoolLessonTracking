package com.schoolLessonTracking.controller;

import com.schoolLessonTracking.businessService.TeacherFavoriteStudentsService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.FavoriteStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacherFavoriteStudents")
public class TeacherFavoriteStudentsController {

    private final TeacherFavoriteStudentsService teacherFavoriteStudentsService;

    @Autowired
    public TeacherFavoriteStudentsController(TeacherFavoriteStudentsService teacherFavoriteStudentsService) {
        this.teacherFavoriteStudentsService = teacherFavoriteStudentsService;
    }

    @GetMapping("/getTeacherFavoriteStudents")
    @CrossOrigin(origins = "*")
    public Result<List<FavoriteStudent>> getAllTeacherFavoriteStudents(@RequestParam String teacherId){
        return  this.teacherFavoriteStudentsService.getAllTeacherFavoriteStudents(teacherId);
    }
}
