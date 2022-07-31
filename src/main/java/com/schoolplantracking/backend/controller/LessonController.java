package com.schoolplantracking.backend.controller;


import com.schoolplantracking.backend.businessService.LessonService;
import com.schoolplantracking.backend.core.Result;
import com.schoolplantracking.backend.entities.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("lessons")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/createLesson")
    public Result createLesson(@Valid @RequestBody Lesson lesson){
        return this.lessonService.createLesson(lesson);
    }

    @GetMapping("/getLessonById")
    public Result getLessonById(@RequestParam String lessonId){
        return this.lessonService.getLessonById(lessonId);
    }

    @GetMapping("/getAllLessons")
    public Result<List<Lesson>> getAllLessons(){
        return this.lessonService.getAllLessons();
    }

    @GetMapping("/getLessonBetween")
    public Result<List<Lesson>> findByLessonDateBetween(@RequestParam Date from,@RequestParam Date to){
        return this.lessonService.findByLessonDateBetween(from, to);
    }
    @GetMapping("/getStudentLesson")
    public Result<List<Lesson>> findByStudentLesson(@RequestParam Date from,@RequestParam Date to,@RequestParam String studentId){
        return this.lessonService.findByStudentLesson(from, to, studentId);
    }

    @GetMapping("/getTeacherLesson")
    public Result<List<Lesson>> findByTeacherLesson(@RequestParam Date from,@RequestParam Date to,@RequestParam String teacherId){
        return this.lessonService.findByTeacherLesson(from, to, teacherId);
    }

    @PutMapping("/updateLesson")
    public Result updateLesson(@Valid @RequestBody Lesson lesson){
        return this.lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson")
    public Result deleteLesson(@RequestParam String lessonId){
        return this.lessonService.deleteLesson(lessonId);
    }
}
