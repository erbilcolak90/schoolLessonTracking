# schoolLessonTracking

This project enables to create a daily lesson program in educational institutions, 
and to create a plan by seeing the daily schedules of teachers and students.
It becomes easier for teachers to follow the lessons of the students they are responsible for. 
Teachers and students are assigned for the created lesson. 
It includes queries such as which student took part in which teacher's lesson on which day and at what time.

### Json Format


[Click Here For Postman Collection.zip](https://github.com/erbilcolak90/schoolLessonTracking/files/9250317/jsonFormat.zip)


[Json Examples] (https://github.com/erbilcolak90/schoolLessonTracking/blob/main/jsonFormatExamples.json)

---

## Entities

- Teacher
- Student
- Lesson
- GetTeacherLessonRequestBody
- GetStudentLessonRequestBody

### Teacher

It is the class created for the teacher in the system.

- Id : ``String``
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- firstName : ``String``
- LastName : ``String``
- profession : ``String``
- profilePicture : ``String``
- responsibleStudentIds : ``List<String>``

### Student

It is the class created for the student in the system. 

- Id : ``String``
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- firstName : ``String``
- LastName : ``String``
- profilePicture : ``String``


### Lesson

It is the class created for the student in the system.

- Id : ``String``
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- teacherId : ``String``
- teacherLessonIndexAtTheDay : ``int``
- lessonDate : ``Date``
- studentList : ``List<String>``

### GetTeacherLessonRequestBody

It is the class for the requestbody in the lesson controller.

- teacherId : ``String``
- from : ``Date``
- to : ``Date``

### GetStudentLessonRequestBody

It is the class for the requestbody in the lesson controller.

- studentId : ``String``
- from : ``Date``
- to : ``Date``


### Rest Api

````
RestController("/teachers")
RestController("/students")
RestController("/lessons")
````

## Create Teacher

### Request

````
method: POST
url: /teachers/createTeacher
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/createTeacher
requestParams: -
requestBody: {
    firstName : String
    lastName: String 
    profession : String 
    profilePicture : String
}
````

### Response

````
{
    "success": true,
    "message": "teacher is a created",
    "data": {
        "id": "1",
        "createDate": "2022-08-03T00:12:32.874+00:00",
        "updateDate": "2022-08-03T00:12:32.874+00:00",
        "firstName": "erbil",
        "lastName": "çolak",
        "profession": "Satranç",
        "profilePicture": "https://media-exp1.licdn.com/dms/image/D4D35AQHX5s-ypVhiZw/profile-framedphoto-shrink_800_800/0/1657997550661?e=1659920400&v=beta&t=Zx34tH2BUS3zCD7r87Q7Ebt2eQTWp8strl7rkTkG2NI",
        "responsibleStudentIds": null,
        "deleted": false
    }
}
````

---

## Get Teacher

### Request

````
method: GET
url: /teachers/getTeacher
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/getTeacher/{teacherId}
requestParams: -
requestBody: -
pathVariable : {teacherId}

````

### Response

````
{
    "success": true,
    "message": "teacher info : ",
    "data": {
        "id": "1",
        "createDate": "2022-08-01T00:03:57.002+00:00",
        "updateDate": "2022-08-01T00:03:57.002+00:00",
        "firstName": "Erbil",
        "lastName": "Çolak",
        "profession": "Satranç",
        "profilePicture": "https://media-exp1.licdn.com/dms/image/D4D35AQHX5s-ypVhiZw/profile-framedphoto-shrink_800_800/0/1657997550661?e=1659920400&v=beta&t=Zx34tH2BUS3zCD7r87Q7Ebt2eQTWp8strl7rkTkG2NI",
        "responsibleStudentIds": [],
        "deleted": false
    }
}
````

---

## Get All Teachers

### Request

````
method: GET
url: /teachers/getAllTeacher
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/getAllTeacher
requestParams: -
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Teacher size : 6",
    "data": [
        {
        "id": "1",
        "createDate": "2022-08-01T00:03:57.002+00:00",
        "updateDate": "2022-08-01T00:03:57.002+00:00",
        "firstName": "Erbil",
        "lastName": "Çolak",
        "profession": "Satranç",
        "profilePicture": "https://media-exp1.licdn.com/dms/image/D4D35AQHX5s-ypVhiZw/profile-framedphoto-shrink_800_800/0/1657997550661?e=1659920400&v=beta&t=Zx34tH2BUS3zCD7r87Q7Ebt2eQTWp8strl7rkTkG2NI",
        "responsibleStudentIds": [],
        "deleted": false
        },
        ...
    ]
}

````

---

## Update Responsible Student

### Request

````
method: PUT
url: /teachers/updateResponsibleStudent
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/updateResponsibleStudent
requestParams: teacherId : String , studentId : String
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Student is added to teacher's responsibility, updated responsibility list :",
    "data": [
        "62e71b46ed4e1366e8cedc7e"
    ]
}
````

---

## Update Teacher

### Request

````
method: PUT
url: /teachers/updateTeacher
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/teachers/updateTeacher
requestParams: -
requestBody: {
        {
    "id" :  String
    "firstName" : String
    "lastName" : String 
    "profilePicture" : String
}
        }

````

### Response

````
{
    "id" : "62e5401cc7ea313549e3e06e",
    "firstName" : "Erbil",
    "lastName" : "Çolak",
    "profilePicture" : "https://media-exp1.licdn.com/dms/image/D4D35AQHX5s-ypVhiZw/profile-framedphoto-shrink_800_800/0/1657997550661?e=1659920400&v=beta&t=Zx34tH2BUS3zCD7r87Q7Ebt2eQTWp8strl7rkTkG2NI"
}
````

---

## Delete Teacher

### Request

````
method: DELETE
url: /teachers/deleteTeacher/{teacherId}
requestSample: https://school-lesson-tracking.herokuapp.com/teachers/deleteTeacher/{teacherId}
requestParams: -
requestBody: -
pathVariable: {teacherId}
````

### Response

````
{
    "success": true,
    "message": "teacher deleted ",
    "data": null
}
````

---

## Create Student

### Request

````
method: POST
url: /students/createStudent
requestSample: https://school-lesson-tracking.herokuapp.com/students/createStudent
requestParams: -
requestBody: {
    firstName : String
    lastName: String  
    profilePicture : String
}
````

### Response

````
{
    "success": true,
    "message": "Student created. Info :",
    "data": {
        "id": "62e9c38c9bc2bc1cb974acd9",
        "createDate": "2022-08-03T00:38:36.402+00:00",
        "updateDate": "2022-08-03T00:38:36.402+00:00",
        "firstName": "faruk nafız",
        "lastName": "çamlıbel",
        "profilePicture": "www.deneme.com/images/12d2t343",
        "deleted": false
    }
}
````

---

## Get Studen By Id

### Request

````
method: GET
url: /students/getStudentById/{studentId}
requestSample: https://school-lesson-tracking.herokuapp.com/students/getStudentById/{studentId}
requestParams: -
requestBody: -
pathVariable : {studentId}

````

### Response

````
{
    "success": true,
    "message": "Student info : ",
    "data": {
        "id": "62e9c38c9bc2bc1cb974acd9",
        "createDate": "2022-08-03T00:38:36.402+00:00",
        "updateDate": "2022-08-03T00:38:36.402+00:00",
        "firstName": "faruk nafız",
        "lastName": "çamlıbel",
        "profilePicture": "www.deneme.com/images/12d2t343",
        "deleted": false
    }
}
````

---

## Get All Students

### Request

````
method: GET
url: /students/getAllStudent
requestSample: https://school-lesson-tracking.herokuapp.com/students/getAllStudent
requestParams: -
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Student List : ",
    "data": [
        {
            "id": "62e71b46ed4e1366e8cedc7e",
            "createDate": "2022-08-01T00:16:06.472+00:00",
            "updateDate": "2022-08-01T00:16:06.472+00:00",
            "firstName": "Adem",
            "lastName": "Alper",
            "profilePicture": "https://media-exp1.licdn.com/dms/image/C4D03AQFiHfLeBivy-w/profile-displayphoto-shrink_800_800/0/1575059954432?e=1665014400&v=beta&t=350VPNVWh3qy5Ti-cdNneqiLQQfPIUyMrPyNgTQMLGo",
            "deleted": false
        },
       ...
    ]
}
````

---

## Update Student

### Request

````
method: PUT
url: /students/updateStudent
requestSample: https://school-lesson-tracking.herokuapp.com/students/updateStudent
requestParams: -
requestBody: {
    id : String
    firstName : String
    lastName: String  
    profilePicture : String
}
````

### Response

````
{
    "success": true,
    "message": "Student is updated",
    "data": {
        "id": "62e9c38c9bc2bc1cb974acd9",
        "createDate": "2022-08-03T00:38:36.402+00:00",
        "updateDate": "2022-08-03T00:47:14.342+00:00",
        "firstName": "Faruk ",
        "lastName": "Çamlıbel",
        "profilePicture": "www.deneme.com/1wf22vf",
        "deleted": false
    }
}
````

---

## Delete Student

### Request

````
method: DELETE
url: /students/deleteStudent
requestSample: https://school-lesson-tracking.herokuapp.com/students/deleteStudent
requestParams: id : String
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Student is deleted",
    "data": null
}
````

---

## Create Lesson

### Request

````
method: POST
url: /lessons/createLesson
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/createLesson
requestParams: -
requestBody: {
    teacherId : String,
    lessonDate : "2022-08-30T00:00:00.186+00:00" ,
    studentList : List<String>
}
````

### Response

````
{
    "success": true,
    "message": "Lesson created ",
    "data": {
        "id": "62e9a7e15f931b35ee103799",
        "createDate": "2022-08-02T22:40:33.139+00:00",
        "updateDate": "2022-08-02T22:40:33.139+00:00",
        "teacherId": "62e7186ded4e1366e8cedc7d",
        "teachersLessonIndexAtTheDay": 0,
        "lessonDate": "2022-08-30T00:00:00.186+00:00",
        "studentList": [
            "62e985694e1d9b07b17b3b93"
        ],
        "deleted": false
    }
}
````

---

## Get Lesson By Id

### Request

````
method: GET
url: /lessons/getLessonById
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/getLessonById
requestParams: lessonId : String
requestBody: - 
````

### Response

````
{
    "success": true,
    "message": "Lesson info : ",
    "data": {
        "id": "62e9a7e15f931b35ee103799",
        "createDate": "2022-08-02T22:40:33.139+00:00",
        "updateDate": "2022-08-02T22:40:33.139+00:00",
        "teacherId": "62e7186ded4e1366e8cedc7d",
        "teachersLessonIndexAtTheDay": 0,
        "lessonDate": "2022-08-30T00:00:00.186+00:00",
        "studentList": [
            "62e985694e1d9b07b17b3b93"
        ],
        "deleted": false
    }
}
````

---

## Get All Lessons

### Request

````
method: GET
url: /lessons/getAllLessons
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/getAllLessons
requestParams: -
requestBody: - 
````

### Response

````
{
    "success": true,
    "message": "Lesson list : ",
    "data": [
        {
            "id": "62e7e50527079c56629ca5b8",
            "createDate": "2022-08-01T14:36:53.818+00:00",
            "updateDate": "2022-08-01T14:36:53.818+00:00",
            "teacherId": "62e7186ded4e1366e8cedc7d",
            "teachersLessonIndexAtTheDay": 0,
            "lessonDate": "2022-08-11T00:00:00.186+00:00",
            "studentList": [
                "62e71b46ed4e1366e8cedc7e"
            ],
            "deleted": false
        },
      ...
    ]
}
````

---

## Get Lesson Between Date

### Request

````
method: GET
url: /lessons/getAllLessonBetween
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/getAllLessonBetween
requestParams: from : Date (2022/10/08 00:00) , to : Date (2022/10/15 00:00)
requestBody: - 
````

### Response

````
{
    "success": true,
    "message": "Lesson list : ",
    "data": [
        {
            "id": "62e7e50527079c56629ca5b8",
            "createDate": "2022-08-01T14:36:53.818+00:00",
            "updateDate": "2022-08-01T14:36:53.818+00:00",
            "teacherId": "62e7186ded4e1366e8cedc7d",
            "teachersLessonIndexAtTheDay": 0,
            "lessonDate": "2022-08-11T00:00:00.186+00:00",
            "studentList": [
                "62e71b46ed4e1366e8cedc7e"
            ],
            "deleted": false
        },
     ...
    ]
}
````

---

## Get Student Lesson

### Request

````
method: GET
url: /lessons/getStudentLesson
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/getStudentLesson
requestParams: -
requestBody: {
    from : Date (2022-08-03T01:08:57.789+00:00),
    to : Date (2022-08-30T01:08:57.789+00:00),
    studentId : String
} 
````

### Response

````
{
    "success": true,
    "message": "Student lesson list : ",
    "data": [
        {
            "id": "62e9caa99bc2bc1cb974acda",
            "createDate": "2022-08-03T01:08:57.789+00:00",
            "updateDate": "2022-08-03T01:08:57.789+00:00",
            "teacherId": "62e7186ded4e1366e8cedc7d",
            "teachersLessonIndexAtTheDay": 0,
            "lessonDate": "2022-08-11T00:00:00.186+00:00",
            "studentList": [
                "62e985694e1d9b07b17b3b93"
            ],
            "deleted": false
        }
        ...
    ]
}
````

---

## Get Teacher Lesson

### Request

````
method: GET
url: /lessons/getTeacherLesson
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/getTeacherLesson
requestParams: -
requestBody: {
    from : Date (2022-08-03T01:08:57.789+00:00),
    to : Date (2022-08-30T01:08:57.789+00:00),
    studentId : String
    } 
````

### Response

````
{
    "success": true,
    "message": "Teacher lesson list : ",
    "data": [
        {
            "id": "62e7e50527079c56629ca5b8",
            "createDate": "2022-08-01T14:36:53.818+00:00",
            "updateDate": "2022-08-01T14:36:53.818+00:00",
            "teacherId": "62e7186ded4e1366e8cedc7d",
            "teachersLessonIndexAtTheDay": 0,
            "lessonDate": "2022-08-11T00:00:00.186+00:00",
            "studentList": [
                "62e71b46ed4e1366e8cedc7e"
            ],
            "deleted": false
        },
        ...
    ]
}
````

---

## Update Lesson

### Request

````
method: PUT
url: /lessons/updateLesson
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/updateLesson
requestParams: -
requestBody:{
    {
    "id" : String,
    "teacherId" : String ,
    "lessonDate" :  Date,
    "studentList": []
} 
````

### Response

````
{
    "success": true,
    "message": "Lesson updated ",
    "data": {
        "id": "62e9ccb49bc2bc1cb974acdb",
        "createDate": "2022-08-03T01:17:40.253+00:00",
        "updateDate": "2022-08-03T01:19:13.475+00:00",
        "teacherId": "62e7186ded4e1366e8cedc7d",
        "teachersLessonIndexAtTheDay": 0,
        "lessonDate": "2022-09-22T13:00:44.186+00:00",
        "studentList": [
            "62e58888db747438546e47b7",
            "62e588b6db747438546e47b8",
            "62e588d0db747438546e47b9"
        ],
        "deleted": false
    }
}
````

---

## Delete Lesson

### Request

````
method: DELETE
url: /lessons/deleteLesson
requestSample: https://school-lesson-tracking.herokuapp.com/lessons/deleteLesson
requestParams: lessonId : String 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Lesson deleted",
    "data": null
}
````

---
