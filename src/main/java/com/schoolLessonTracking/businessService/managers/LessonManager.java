package com.schoolLessonTracking.businessService.managers;

import com.schoolLessonTracking.core.Result;
import com.schoolLessonTracking.entities.*;
import com.schoolLessonTracking.businessService.LessonService;
import com.schoolLessonTracking.repositories.LessonRepository;
import com.schoolLessonTracking.repositories.StudentRepository;
import com.schoolLessonTracking.repositories.TeacherFavoriteStudentsRepository;
import com.schoolLessonTracking.repositories.TeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@NoArgsConstructor
@Service
public class LessonManager implements LessonService {

    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    private TeacherFavoriteStudentsRepository teacherFavoriteStudentsRepository;

    @Autowired
    public LessonManager(LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, TeacherFavoriteStudentsRepository teacherFavoriteStudentsRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.teacherFavoriteStudentsRepository = teacherFavoriteStudentsRepository;
    }


    //POST METHODS
    @Override
    public Result createLesson(Lesson lesson) {
        try {
            Calendar startTempLessonDateFrom = GregorianCalendar.getInstance(); // creates a new calendar instance
            startTempLessonDateFrom.setTime(lesson.getLessonDate());
            startTempLessonDateFrom.set(Calendar.HOUR_OF_DAY, 0);
            startTempLessonDateFrom.set(Calendar.MINUTE, 0);
            startTempLessonDateFrom.set(Calendar.SECOND, 0);
            Date from = startTempLessonDateFrom.getTime();

            Calendar startTempLessonDateTo = GregorianCalendar.getInstance(); // creates a new calendar instance
            startTempLessonDateTo.setTime(lesson.getLessonDate());
            startTempLessonDateTo.set(Calendar.HOUR_OF_DAY, 23);
            startTempLessonDateTo.set(Calendar.MINUTE, 59);
            startTempLessonDateTo.set(Calendar.SECOND, 59);
            Date to = startTempLessonDateTo.getTime();


            //O güne ait tüm derslerin listesi
            List<Lesson> lessonThatDay = this.lessonRepository.findByLessonDateBetween(from, to);
            //oluşturulan derse ait öğretmenin geçici tempteacher objesine ekliyoruz.
            Teacher enteredTeacher = this.teacherRepository.findById(lesson.getTeacherId()).orElseThrow();

            //Öğretmenin o günkü tüm derslerini içeren liste
            List<Lesson> enteredTeachersLessonThatDay = new ArrayList<Lesson>();

            //Öğretmenin o günkı derslerinde yer alan öğrencilerin studentId leri
            List<String> studentIdsListofThatDay = new ArrayList<String>();


            //O günkü tüm dersler
            for (Lesson lessonThatDayItem : lessonThatDay) {
                //Eğer o günkü ders içersindeki öğretmen Id si ile tempTeacherId
                if (lessonThatDayItem.getTeacherId().equals(enteredTeacher.getId())) {
                    //O gün öğretmenin verdiği derslerin listesi
                    enteredTeachersLessonThatDay.add(lessonThatDayItem);
                    //lesson da yer alan studentId ler studentIdlist e eklenir.
                    studentIdsListofThatDay.addAll(lessonThatDayItem.getStudentList());

                }
            }

            //öğretmenin o günkü ders sayısı 8 den fazlaysa ders oluşturulamaz
            if (enteredTeachersLessonThatDay.size() > 8) {
                return new Result<>(false, "Teacher have lesson this day 8 hours", null);
            } else {

                for (String studentItem : studentIdsListofThatDay) {
                    int frequence = Collections.frequency(studentIdsListofThatDay, studentItem);
                    if (frequence > 2) {

                        return new Result<>(false, "This student cannot take anymore lesson from this teacher", studentItem);
                    }
                }

            }

            lesson.setCreateDate(new Date());
            lesson.setUpdateDate(new Date());
            lesson.setDeleted(false);

            this.lessonRepository.save(lesson);

            TeacherFavoriteStudents teacherFavoriteStudents = this.teacherFavoriteStudentsRepository.findByTeacherId(lesson.getTeacherId());


            if(teacherFavoriteStudents == null){
                TeacherFavoriteStudents tempTeacherFavoriteStudents = new TeacherFavoriteStudents();
                tempTeacherFavoriteStudents.setTeacherId(lesson.getTeacherId());


                List<FavoriteStudent> tempFavoriteStudentList = new ArrayList<>();

                for(String lessonStudentId : lesson.getStudentList()){

                    FavoriteStudent tempFavoriteStudent = new FavoriteStudent(lessonStudentId,1);

                    tempFavoriteStudentList.add(tempFavoriteStudent);

                }

                tempTeacherFavoriteStudents.setFavoriteStudentList(tempFavoriteStudentList);

                this.teacherFavoriteStudentsRepository.save(tempTeacherFavoriteStudents);
            }

            else{

                //derse giren öğrenciler listesi
                for(String lessonStudentId: lesson.getStudentList()){
                    boolean isStudentexist = false;
                    for(FavoriteStudent favoriteStudent: teacherFavoriteStudents.getFavoriteStudentList()){
                        if(favoriteStudent.getStudentId().equals(lessonStudentId)){
                            isStudentexist = true;
                            favoriteStudent.setLessonCount(favoriteStudent.getLessonCount()+1);
                            this.teacherFavoriteStudentsRepository.save(teacherFavoriteStudents);
                        }

                    }
                    if(!isStudentexist){
                        FavoriteStudent tempFavoriteStudent = new FavoriteStudent(lessonStudentId,1);
                        teacherFavoriteStudents.getFavoriteStudentList().add(tempFavoriteStudent);
                        this.teacherFavoriteStudentsRepository.save(teacherFavoriteStudents);
                    }
                }

            }

            return new Result<>(true, "Lesson created ", lesson);


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
            Calendar tempFrom = GregorianCalendar.getInstance(); // creates a new calendar instance
            tempFrom.setTime(from);
            tempFrom.set(Calendar.HOUR_OF_DAY, 0);
            tempFrom.set(Calendar.MINUTE, 0);
            tempFrom.set(Calendar.SECOND, 0);
            Date _from = tempFrom.getTime();

            Calendar tempTo = GregorianCalendar.getInstance(); // creates a new calendar instance
            tempTo.setTime(to);
            tempTo.set(Calendar.HOUR_OF_DAY, 23);
            tempTo.set(Calendar.MINUTE, 59);
            tempTo.set(Calendar.SECOND, 59);
            Date _to = tempTo.getTime();

            List<Lesson> lessonList = this.lessonRepository.findByLessonDateBetween(_from, _to);

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

            for (Lesson lesson : lessonList) {
                if (lesson.getTeacherId().equals(teacherId)) {
                    tempLessonList.add(lesson);
                }
            }

            return new Result<List<Lesson>>(true, "Teacher lesson list : ", tempLessonList);

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

                tempLesson.setUpdateDate(new Date());
                tempLesson.setLessonDate(lesson.getLessonDate());
                tempLesson.setTeacherId(lesson.getTeacherId());
                tempLesson.setStudentList(lesson.getStudentList());
                tempLesson.setGroupLesson(lesson.isGroupLesson());
                this.lessonRepository.save(tempLesson);

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
            return new Result<>(true, "Lesson deleted", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
