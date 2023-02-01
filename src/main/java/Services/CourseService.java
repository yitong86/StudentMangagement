package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.CourseDao;
import models.Course;
import models.StudentCoursesID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService extends ConnectionDao implements CourseDao {

    @Override
    public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM course");
            List<Course> list = new ArrayList<>();
            while(rs.next())
            {

                Course c = new Course();
                c.setcId(rs.getInt("cId"));
                c.setcName(rs.getString("cName"));
                c.setcInstructorName(rs.getString("cInstructorName"));

                list.add(c);
            }
            return list;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public void save(List<Course> courseList) {
//        try {
//            Connection con = ConnectionDao.getConnection();
//            for(Course i : courseList) {
//                String sqlQuery = "INSERT INTO student_courses (s_email,c_id) VALUES (?,?)";
//                PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
//                prepStmt.setInt(1,  i.getcId());
//                prepStmt.setString(1,  i.getcName());
//                prepStmt.setString(2, i.getcInstructorName());
//
//
//                int affectedRows = prepStmt.executeUpdate();
//                System.out.println(affectedRows + " row(s) affected !!");
//            }
//        }
//        catch (ClassNotFoundException e)
//        {
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    }


