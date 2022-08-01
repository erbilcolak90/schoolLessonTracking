package com.schoolLessonTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("students")
public class Student {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    @NotBlank(message = "this field cannot blank")
    @Size(min = 3, max = 20, message = "First name size min.= 3 max=20")
    private String firstName;
    @NotBlank(message = "this field cannot blank")
    @Size(min = 3, max = 20, message = "Last name size min.= 3 max=20")
    private String lastName;
    private String profilePicture;

}
