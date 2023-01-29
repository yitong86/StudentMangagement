package jpa.dao;

import models.Course;
import java.util.*;

import java.sql.SQLException;

public interface CourseDao {
   List<Course> getAllCourses() throws ClassNotFoundException, SQLException;

}
