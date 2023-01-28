package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.StudentDao;
import models.Course;
import models.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService extends ConnectionDao implements StudentDao {


    @Override
    public List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            List<Student> studentList = new ArrayList();
            while (rs.next()) {
                Student s = new Student();

                s.setsName(rs.getString("sName"));
                s.setsEmail(rs.getString("sEmail"));
                s.setsPass(rs.getString("sPass"));

                studentList.add(s);
            }
            return studentList;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentByEmail(List<Student> studentList, String studentEmail) {


        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail)) {
                return student;
            }
        }

        return null;

    }

    @Override
    public boolean validateStudent(List<Student> studentList, String studentEmail, String studentPass) {
        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail) && student.getsPass().equals(studentPass)) {
                ;
                return true;
            }


        }
        return false;
    }

//
//    @Override
//    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException, ClassNotFoundException {
//        String sqlStatement = "INSERT INTO StudentRegistration VALUES('" + studentEmail + "','" + courseId + "')";
//    }
//
//    @Override
//    public List<Course> getStudentCourses(String studentEmail) {
//        return null;
//    }


    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Course getStudentCourses(int id) {
//one student get course by id
        Course c = new Course();
        Student s = new Student();
        List<Course> list = new ArrayList<>();
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM course where cId =?");

            while(rs.next())
            {
                c.setcId(rs.getInt("cId"));
                c.setcName(rs.getString("cName"));
                c.setcInstructorName(rs.getString("cInstructorName"));


            }

            return c;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
