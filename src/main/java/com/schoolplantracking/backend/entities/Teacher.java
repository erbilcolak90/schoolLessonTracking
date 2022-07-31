package com.schoolplantracking.backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Document("teachers")
public class Teacher {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    @NotBlank (message = "this field cannot blank")
    @Size(min = 3,max = 20,message = "First name size min.= 3 max=20")
    private String firstName;
    @NotBlank (message = "this field cannot blank")
    @Size(min = 3,max = 20,message = "Last name size min.= 3 max=20")
    private String lastName;
    private String profession;
    private String profilePicture;
    private List<String> responsibleStudentIds = new ArrayList<String>();

}
