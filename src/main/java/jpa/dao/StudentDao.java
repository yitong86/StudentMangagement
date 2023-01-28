package jpa.dao;

import models.Course;
import models.Student;

import java.sql.SQLException;
import java.util.*;
public interface StudentDao {
   List<Student> getAllStudents() throws ClassNotFoundException, SQLException;;

     Student getStudentByEmail(List<Student> studentList,String studentEmail) throws SQLException, ClassNotFoundException;

    boolean validateStudent(List<Student> studentList,String studentEmail, String studentPass) throws SQLException, ClassNotFoundException;

    void registerStudentToCourse(String studentEmail,int courseId) throws SQLException, ClassNotFoundException;

    Course getStudentCourses(int id);
}
