package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.StudentDao;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentService extends ConnectionDao implements StudentDao {


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

    public Student getStudentByEmail(String studentEmail) throws ClassNotFoundException, SQLException {
        List<Student> b = new ArrayList<>();
        Student s = new Student();
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student where sEmail= 'abc@abc.com'");

            while (rs.next()) {
                s.setsPass(rs.getString("sPass"));
                s.setsEmail(rs.getString("sEmail"));
                s.setsName(rs.getString("sName"));

            }
            // b.add(s);
            return s;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean validateStudent(List<Student> studentList, String studentEmail, String studentPass) throws ClassNotFoundException, SQLException {
        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail) && student.getsPass().equals(studentPass)) {

                return true;
            }
        }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException, ClassNotFoundException {


        Connection con = ConnectionDao.getConnection();
        String sqlQuery = "SELECT * FROM student_courses WHERE s_email = ? AND c_id = ?";
        PreparedStatement stmt = con.prepareStatement(sqlQuery);

        try {
            stmt.setString(1, studentEmail);
            stmt.setInt(2, courseId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Student is already registered for this course");
                return;
            }

            // Add student to the join table
            stmt = con.prepareStatement("INSERT INTO student_courses (s_email, c_id) VALUES (?, ?)");
            stmt.setString(1, studentEmail);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();

            System.out.println("Student successfully registered for the course");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Course> getStudentCourses(List<Course> courseList, String email, List<StudentCoursesID> list) {
        List<Course> courses = new ArrayList<>();
        for (StudentCoursesID listArray : list) {
            if (listArray.geteMail().equals(email)) {
                for (Course c : courseList) {
                    if (c.getcId() == listArray.getCourseID()) {
                        courses.add(c);
                    }

                }
            }

        }
        return courses;
    }


}

