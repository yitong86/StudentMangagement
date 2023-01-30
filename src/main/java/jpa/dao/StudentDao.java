package jpa.dao;

import models.Course;
import models.Student;
import models.StudentCoursesID;

import java.sql.SQLException;
import java.util.*;
public interface StudentDao {
   List<Student> getAllStudents() throws ClassNotFoundException, SQLException;;

    Student getStudentByEmail(String studentEmail) throws SQLException, ClassNotFoundException;

    boolean validateStudent(List<Student> studentList ,String studentEmail, String studentPass) throws SQLException, ClassNotFoundException;

    List<StudentCoursesID> registerStudentToCourse(List<StudentCoursesID> studentCoursesID, String studentEmail, int courseId) throws SQLException, ClassNotFoundException;

    List<Course> getStudentCourses(List<StudentCoursesID> studentCoursesIDS, String sEmail, List<Course> list);
}
