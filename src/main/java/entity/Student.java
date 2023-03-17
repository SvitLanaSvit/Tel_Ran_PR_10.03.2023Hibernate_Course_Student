package entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private Timestamp dateStarted;
    private String groupStudent;

    @OneToMany(mappedBy = "student")
    private Set<StudentCourses> studentCourses = new HashSet<>();

    public Student(String firstname, String lastname, int age, Timestamp dateStarted, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.dateStarted = dateStarted;
        this.groupStudent = group;
    }

//    public void addCourse(Course course){
//        if(course == null){
//            throw new NullPointerException("It was putting nullable course!!!");
//        }
//        courses.add(course);
//        course.addStudent(this);
//    }
    public void addStudentCourses(StudentCourses sc){
        if(sc == null){
            throw new NullPointerException("It was putting nullable sc!!!");
        }
        studentCourses.add(sc);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", age ='" + age + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateStarted=" + dateStarted +
                ", groupStudent='" + groupStudent + '\'' +
                '}';
    }
}