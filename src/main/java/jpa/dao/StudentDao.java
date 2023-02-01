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

    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException, ClassNotFoundException;

    List<Course> getStudentCourses(List<Course> courseList,String email, List<StudentCoursesID> list)  throws SQLException, ClassNotFoundException;
}
