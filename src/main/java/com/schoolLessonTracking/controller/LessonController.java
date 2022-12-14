package com.schoolLessonTracking.controller;


import com.schoolLessonTracking.businessService.LessonService;
import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.GetLessonBetweenRequestBody;
import com.schoolLessonTracking.entities.GetStudentLessonRequestBody;
import com.schoolLessonTracking.entities.GetTeacherLessonRequestBody;
import com.schoolLessonTracking.entities.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
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
    @CrossOrigin(origins = "*")
    public Result createLesson(@Valid @RequestBody Lesson lesson){
        return this.lessonService.createLesson(lesson);
    }

    @GetMapping("/getLessonById")
    @CrossOrigin(origins = "*")
    public Result getLessonById(@RequestParam String lessonId){
        return this.lessonService.getLessonById(lessonId);
    }

    @GetMapping("/getAllLessons")
    @CrossOrigin(origins = "*")
    public Result<List<Lesson>> getAllLessons(){
        return this.lessonService.getAllLessons();
    }

    @PostMapping("/getLessonBetween")
    @CrossOrigin(origins = "*")
    public Result<List<Lesson>> findByLessonDateBetween(@RequestBody GetLessonBetweenRequestBody getLessonBetweenRequestBody){
        Date from = getLessonBetweenRequestBody.getFrom();
        Date to = getLessonBetweenRequestBody.getTo();

        return this.lessonService.findByLessonDateBetween(from, to);
    }
    @PostMapping("/getStudentLesson")
    @CrossOrigin(origins = "*")
    public Result<List<Lesson>> findByStudentLesson(@Valid @RequestBody GetStudentLessonRequestBody getStudentLessonRequestBody){
        Date from = getStudentLessonRequestBody.getFrom();
        Date to = getStudentLessonRequestBody.getTo();
        String studentId = getStudentLessonRequestBody.getStudentId();
        return this.lessonService.findByStudentLesson(from, to, studentId);
    }

    @PostMapping("/getTeacherLesson")
    @CrossOrigin(origins = "*")
    public Result<List<Lesson>> findByTeacherLesson(@Valid @RequestBody GetTeacherLessonRequestBody getTeacherLessonRequestBody){
        Date from = getTeacherLessonRequestBody.getFrom();
        Date to = getTeacherLessonRequestBody.getTo();
        String teacherId = getTeacherLessonRequestBody.getTeacherId();
        return this.lessonService.findByTeacherLesson(from,to,teacherId);
    }

    @PutMapping("/updateLesson")
    @CrossOrigin(origins = "*")
    public Result updateLesson(@RequestBody Lesson lesson){
        return this.lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson")
    @CrossOrigin(origins = "*")
    public Result deleteLesson(@RequestParam String lessonId){
        return this.lessonService.deleteLesson(lessonId);
    }
}
