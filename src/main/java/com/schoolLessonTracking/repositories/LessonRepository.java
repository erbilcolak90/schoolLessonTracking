package com.schoolLessonTracking.repositories;

import com.schoolLessonTracking.entities.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends MongoRepository<Lesson,String> {

    List<Lesson> findByLessonDateBetween(Date from, Date to);

}
