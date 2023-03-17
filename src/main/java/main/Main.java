package main;

import entity.Course;
import entity.Student;
import entity.StudentCourses;
import enums.TypeCourse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Timestamp;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        Course course1 = new Course("English", TypeCourse.LANGUAGE, 300);
        Course course2 = new Course("Java", TypeCourse.IT, 900);
        Course course3 = new Course("JavaScript", TypeCourse.IT, 700);

        Student student1 =
                new Student("Lana", "Johnson", 25, Timestamp.valueOf("2022-03-10 00:00:00"), "PV122");
        Student student2 =
                new Student("Dmytro", "Wojt", 18, Timestamp.valueOf("2022-02-10 00:00:00"), "WV122");
        Student student3 =
                new Student("Alex", "Timberlate", 23, Timestamp.valueOf("2020-01-10 00:00:00"), "NV120");
        Student student4 =
                new Student("Victor", "Dack", 35, Timestamp.valueOf("2021-03-10 00:00:00"), "KV121");
        Student student5 =
                new Student("Elena", "Schmidt", 19, Timestamp.valueOf("2021-05-10 00:00:00"), "SV121");

        StudentCourses sc1 = new StudentCourses(course1, student1, Timestamp.valueOf("2023-04-23 10:10:10.0"), Timestamp.valueOf("2023-09-23 10:10:10.0"));
        StudentCourses sc2 = new StudentCourses(course2, student1, Timestamp.valueOf("2023-05-23 10:10:10.0"), Timestamp.valueOf("2023-10-23 10:10:10.0"));
        StudentCourses sc3 = new StudentCourses(course3, student1, Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-11-23 10:10:10.0"));
        StudentCourses sc4 = new StudentCourses(course2, student2, Timestamp.valueOf("2023-08-23 10:10:10.0"), Timestamp.valueOf("2023-12-23 10:10:10.0"));
        StudentCourses sc5 = new StudentCourses(course3, student2, Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-11-23 10:10:10.0"));
        StudentCourses sc6 = new StudentCourses(course1, student3, Timestamp.valueOf("2023-04-23 10:10:10.0"), Timestamp.valueOf("2023-09-23 10:10:10.0"));
        StudentCourses sc7 = new StudentCourses(course3, student4, Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-11-23 10:10:10.0"));
        StudentCourses sc8 = new StudentCourses(course2, student5, Timestamp.valueOf("2023-07-23 10:10:10.0"), Timestamp.valueOf("2023-11-23 10:10:10.0"));

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.FINEST);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        session.save(student5);

        session.save(course1);
        session.save(course2);
        session.save(course3);

        session.save(sc1);
        session.save(sc2);
        session.save(sc3);
        session.save(sc4);
        session.save(sc5);
        session.save(sc6);
        session.save(sc7);
        session.save(sc8);

        transaction.commit();
        session.close();

//        System.out.println();
//        getStudents("Lana");
//        System.out.println();
//        getCourses("English");

//        getCourses("Lana");
//        getStudents("Java");
        getStudents(Timestamp.valueOf("2023-05-23 00:00:00.0"));
    }

    static private void getCourses(String name){
        var session = HibernateUtil.getSessionFactory().openSession();
        var query = session.createQuery("SELECT sc.course.courseName FROM StudentCourses sc WHERE sc.student.firstname = :name");
        query.setParameter("name", name);
        var list = query.getResultList();
        list.forEach(t -> System.out.println(t));
        session.close();
    }

    static private void getStudents(String course){
        int age = 23;
        var session = HibernateUtil.getSessionFactory().openSession();
        var query = session
                .createQuery("SELECT sc.student  FROM StudentCourses sc WHERE sc.course.courseName = :course AND sc.student.age > :age", Student.class);
        query.setParameter("course", course);
        query.setParameter("age", age);
        var list = query.getResultList();
        list.forEach(t -> System.out.println(t.getFirstname() + " " + t.getLastname() + ", " + t.getAge()));
        session.close();
    }

    static private void getStudents(Timestamp date){
        var session = HibernateUtil.getSessionFactory().openSession();
        var query = session
                .createQuery("SELECT sc.student FROM StudentCourses sc WHERE :date BETWEEN sc.dateBegin AND sc.dateEnd", Student.class);
        query.setParameter("date", date);
        var list = query.getResultList();
        list.forEach(s -> System.out.println(s.getFirstname() + " " + s.getLastname()));
    }

//    static private void getStudents(String name){
//        var session = HibernateUtil.getSessionFactory().openSession();
//        var query = session.createQuery("SELECT DISTINCT s FROM Student s JOIN s.courses c WHERE s.firstname = :name");
//        query.setParameter("name", name);
//        var list = query.getResultList();
//
//        //System.out.println(list);
//    }

//    static private void getCourses(String course){
//        var session = HibernateUtil.getSessionFactory().openSession();
//        var query = session.createQuery("SELECT c FROM Course c JOIN c.students s WHERE c.courseName=:course", Course.class);
//        query.setParameter("course", course);
//        var list = query.getResultList();
//        list.forEach(t -> System.out.println(t.getCourseName() + " : " + t.getDuration()));
//        System.out.println(list);
//    }
}