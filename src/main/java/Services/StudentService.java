package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.StudentDao;
import models.Course;
import models.CourseRegisterKey;
import models.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentService extends ConnectionDao implements StudentDao {
    private CourseRegisterKey courses;

    @Override
    public List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            List<Student> studentList = new ArrayList<>();
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

    public Student getStudentByEmail(List<Student> studentList, String studentEmail) throws ClassNotFoundException, SQLException {

        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail)) {
                return student;
            }
        }

        return null;

    }

    @Override
    public boolean validateStudent(List<Student> studentList, String studentEmail, String studentPass) throws ClassNotFoundException {
        Connection con = ConnectionDao.getConnection();
        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail) && student.getsPass().equals(studentPass)) {

                return true;
            }
        }
        return false;
    }


    @Override
    public void registerStudentToCourse(List<CourseRegisterKey> courseRegisterKeys, String studentEmail, int courseId) throws ClassNotFoundException {
//check if a student with this email is already registered the course with cId
        //if not, add to the join table
        Connection con = ConnectionDao.getConnection();
        for (CourseRegisterKey i : courseRegisterKeys) {
            if (i.getsEmail().equals(studentEmail) && i.getcId() == (courseId)) {
                return;
            }
        }
        CourseRegisterKey courseRegisterKey = new CourseRegisterKey();
        courseRegisterKey.setsEmail(studentEmail);
        courseRegisterKey.setcId(courseId);
        courseRegisterKeys.add(courseRegisterKey);

    }

    @Override
    public List<Course> getStudentCourses(List<Course> courseList, List<CourseRegisterKey> courseRegisterKeys, String sEmail) {
        List<Course> newCourses = new ArrayList<>();
        for (CourseRegisterKey i : courseRegisterKeys) {
            if (i.getsEmail().equals(sEmail)) {
//check id match
                for (Course j : courseList) {
                    if (j.getcId() == i.getcId()) {
                        newCourses.add(j);

                    }
                }
            }
        }

        return newCourses;

    }
}