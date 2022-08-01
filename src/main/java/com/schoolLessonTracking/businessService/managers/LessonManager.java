package com.schoolLessonTracking.businessService.managers;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.Student;
import com.schoolLessonTracking.businessService.LessonService;
import com.schoolLessonTracking.entities.Lesson;
import com.schoolLessonTracking.entities.Teacher;
import com.schoolLessonTracking.repositories.LessonRepository;
import com.schoolLessonTracking.repositories.StudentRepository;
import com.schoolLessonTracking.repositories.TeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class LessonManager implements LessonService {

    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;

    private TeacherRepository teacherRepository;

    @Autowired
    public LessonManager(LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    //POST METHODS
    @Override
    public Result createLesson(Lesson lesson) {
            Date date = new Date();
        try {
            Date startTempLessonDate =lesson.getLessonDate();

            Long time = lesson.getLessonDate().getTime();
            Date from = new Date(time - time % (24 * 60 * 60 * 1000));
            Date to = new Date(time + (24 * 60 * 60 * 1000));

            List<Lesson> lessonList=this.lessonRepository.findByLessonDateBetween(from,to);
            Teacher tempTeacher = this.teacherRepository.findById(lesson.getTeacherId()).orElseThrow();

            List<Lesson> tempLessonList = new ArrayList<Lesson>();

            List<String> studentIdsList = new ArrayList<String>();

            //bir öğretmenin günlük verdiği liste
            for(Lesson lessonItem: lessonList){
                if(lessonItem.getTeacherId().equals(tempTeacher.getId())){
                    tempLessonList.add(lessonItem);
                    for(String studentId : lessonItem.getStudentList()){
                        studentIdsList.add(studentId);
                    }
                }
            }


            int lessonCountOfStudent =0;
            //bir öğrenci 2 kereden fazla aynı öğretmenden ders almamalı
            for(String studentId: studentIdsList){
                if(lesson.getStudentList().contains(studentId)){
                    lessonCountOfStudent++;
                }
            }

            if(tempLessonList.size() > 8){
                return new Result<>(false,"Teacher have lesson this day 8 hours",null);
            }
            else{

                if(lessonCountOfStudent>2){
                    lessonCountOfStudent=0;
                    return new Result<>(false,"Student is already take lesson from this teacher 2 times.Please change teacher or student or date ",null);
                }

                lesson.setCreateDate(new Date());
                lesson.setUpdateDate(new Date());
                lesson.setDeleted(false);
                this.lessonRepository.save(lesson);
                return new Result<>(true, "Lesson created", lesson);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // GET METHODS
    @Override
    public Result getLessonById(String lessonId) {
        try {
            Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow();
            return new Result<>(true, "Lesson info : ", lesson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Lesson>> getAllLessons() {
        try {
            return new Result<List<Lesson>>(true, "Lesson list : ", this.lessonRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Lesson>> findByLessonDateBetween(Date from, Date to) {
        try {

            List<Lesson> lessonList = this.lessonRepository.findByLessonDateBetween(from, to);

            return new Result<List<Lesson>>(true, "Lesson list : ", lessonList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Lesson>> findByStudentLesson(Date from, Date to, String studentId) {
        try {
            List<Lesson> lessonList = this.lessonRepository.findByLessonDateBetween(from, to);
            Student student = this.studentRepository.findById(studentId).orElseThrow();

            List<Lesson> tempLesson = new ArrayList<Lesson>();

            for (Lesson lesson : lessonList) {
                if (lesson.getStudentList().contains(studentId)) {
                    tempLesson.add(lesson);
                }
            }
            return new Result<List<Lesson>>(true, "Student lesson list : ", tempLesson);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Lesson>> findByTeacherLesson(Date from, Date to, String teacherId) {
        try {
            List<Lesson> lessonList = this.lessonRepository.findByLessonDateBetween(from, to);
            Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();

            List<Lesson> tempLessonList = new ArrayList<Lesson>();

            for(Lesson lesson: lessonList){
                if(lesson.getTeacherId().equals(teacherId)){
                    tempLessonList.add(lesson);
                }
            }

            return new Result<List<Lesson>>(true,"Teacher lesson list : ",tempLessonList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //PUT METHODS
    @Override
    public Result updateLesson(Lesson lesson) {
        try {
            Lesson tempLesson = this.lessonRepository.findById(lesson.getId()).orElseThrow();

            Date today = new Date();
            if (tempLesson.getLessonDate().after(today)) {
                return new Result<>(true, "this lesson passed.", null);
            } else {
                tempLesson.setUpdateDate(new Date());
                tempLesson.setLessonDate(lesson.getLessonDate());
                tempLesson.setTeacherId(lesson.getTeacherId());
                tempLesson.setStudentList(lesson.getStudentList());
                this.lessonRepository.save(tempLesson);

            }

            return new Result<>(true, "Lesson updated ", tempLesson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // DELETE METHODS
    @Override
    public Result deleteLesson(String lessonId) {
        try {
            Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow();
            lesson.setUpdateDate(new Date());
            lesson.setDeleted(true);
            this.lessonRepository.save(lesson);
            return new Result<>(true, "Lesson deleted", lesson.isDeleted());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
