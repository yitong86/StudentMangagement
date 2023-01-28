package Services;

import jpa.dao.ConnectionDao;
import jpa.dao.CourseDao;
import models.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseService extends ConnectionDao implements CourseDao {

    @Override
    public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {
        try {
            Connection connection = ConnectionDao.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM course");
            List<Course> boollist = new ArrayList<>();
            while(rs.next())
            {

                Course c = new Course();
                c.setcId(rs.getInt("cId"));
                c.setcName(rs.getString("cName"));
                c.setcInstructorName(rs.getString("cInstructorName"));

                boollist.add(c);
            }
            return boollist;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public Course getCourseById(int id) throws SQLException {
//       Course c = new Course();
//        try {
//            Connection connection = ConnectionDao.getConnection();
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM course where cId = ?");
//
//            while(rs.next())
//            {
//                c.setcId(rs.getInt("cId"));
//                c.setcName(rs.getString("cName"));
//                c.setcInstructorName(rs.getString("cInstructorName"));
//
//
//            }
//            return c;
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    }


