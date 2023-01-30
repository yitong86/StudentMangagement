package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.StudentDao;
import models.*;

import java.sql.*;
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

    public Student getStudentByEmail(String studentEmail) throws ClassNotFoundException, SQLException {
        List<Student> b = new ArrayList<>();
        Student s = new Student();
        try {
            Connection connection =ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student where sEmail= 'abc@abc.com'");

            while(rs.next())
            {
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
    public boolean validateStudent(List<Student> studentList ,String studentEmail, String studentPass) throws ClassNotFoundException, SQLException {
        for (Student student : studentList) {
            if (student.getsEmail().equals(studentEmail) && student.getsPass().equals(studentPass)) {

                return true;
            }
        }
        return false;
    }
    @Override
    public List<Course> getStudentCourses(List<StudentCoursesID> studentCoursesIDS,String sEmail,List<Course> list) {

            List<Course> newCourses = new ArrayList<>();

        for (StudentCoursesID i : studentCoursesIDS ) {
            if (i.geteMail().equals(sEmail)) {
//check id match
                for (Course j : list) {
                    if (j.getcId() == i.getCourseID()) {
                        newCourses.add(j);

                    }
                }
            }
        }

        return newCourses;

    }

    @Override
    public List<StudentCoursesID> registerStudentToCourse(List<StudentCoursesID> studentCoursesIDS, String studentEmail, int courseId) throws ClassNotFoundException {
//check if a student with this email is already registered the course with cId
        //if not, add to the join table


        for (StudentCoursesID i : studentCoursesIDS) {
            if (i.geteMail().equals(studentEmail) && i.getCourseID() == (courseId)) {
                return null;
            }
        }
        StudentCoursesID studentCoursesID = new StudentCoursesID();
        studentCoursesID.setCourseID(courseId);
        studentCoursesID.seteMail(studentEmail);
       // if(studentCoursesIDS.contains())
        studentCoursesIDS.add(studentCoursesID);
//StudentCoursesID{eMail='aiannitti7@is.gd', courseID=1}


        save(studentCoursesIDS);
        return studentCoursesIDS;
    }
    public void save(List<StudentCoursesID> ItemList) {
        try {
            Connection con = ConnectionDao.getConnection();
            for(StudentCoursesID i : ItemList) {
                String sqlQuery = "INSERT INTO student_courses (s_email,c_id) VALUES (?,?)";
                PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
                prepStmt.setString(1,  i.geteMail());
                prepStmt.setDouble(2, i.getCourseID());


                int affectedRows = prepStmt.executeUpdate();
                System.out.println(affectedRows + " row(s) affected !!");
            }
        }
        catch (ClassNotFoundException e)
        {
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public void registerStudentInCourse(Student student, Course course) {
//        if (student.getCourses().contains(course)) {
//            System.out.println("You are already registered in that course!");
//        } else {
//            student.getCourses().add(course);
//            System.out.println("Student registered in course successfully");
//        }
//        System.out.println("Updated course list: " + student.getCourses());
//    }


}