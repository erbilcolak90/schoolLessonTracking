package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.FutureOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("lessons")
public class Lesson {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    private String teacherId;
    private int teachersLessonIndexAtTheDay;
    @FutureOrPresent
    private Date lessonDate;
    private List<String> studentList= new ArrayList<String>();
}
