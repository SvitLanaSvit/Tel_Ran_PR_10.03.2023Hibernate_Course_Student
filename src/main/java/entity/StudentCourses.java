package entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "student_courses")
public class StudentCourses {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "date_registration")
    private Timestamp dateRegistration;
    @Column(name = "date_begin")
    private Timestamp dateBegin;
    @Column(name = "date_end")
    private Timestamp dateEnd;

    public StudentCourses(Course course, Student student, Timestamp dateBegin, Timestamp dateEnd) {
        this.course = course;
        this.student = student;
        this.dateRegistration = Timestamp.from(ZonedDateTime.now().toInstant());
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        student.addStudentCourses(this);
        course.addStudentCourses(this);
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private  Course course;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


}
