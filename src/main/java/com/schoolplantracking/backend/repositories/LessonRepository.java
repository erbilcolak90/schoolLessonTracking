package com.schoolplantracking.backend.repositories;

import com.schoolplantracking.backend.entities.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends MongoRepository<Lesson,String> {

    List<Lesson> findByLessonDateBetween(Date from,Date to);

}
