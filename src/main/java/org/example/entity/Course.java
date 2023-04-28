package org.example.entity;

import org.example.enums.TypeCourse;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    @Enumerated(EnumType.STRING)
    private TypeCourse typeCourse;
    private int duration;

    @OneToMany(mappedBy = "course")
    private Set<StudentCourses> studentCourses = new HashSet<>();

    public Course(String courseName, TypeCourse typeCourse, int duration) {
        this.courseName = courseName;
        this.typeCourse = typeCourse;
        this.duration = duration;
    }

    public void addStudentCourses(StudentCourses sc){
        if(sc == null){
            throw new NullPointerException("It was putting nullable sc!!!");
        }
        studentCourses.add(sc);
    }

    @Override
    public String toString() {
//        return String.format("%-5d, %-20s, %-15s, %-10d\n",
//                id, courseName, typeCourse, duration);
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", typeCourse=" + typeCourse +
                ", duration=" + duration +
                '}';
    }
}