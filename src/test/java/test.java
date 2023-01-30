import Services.CourseService;
import models.Course;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class test {


    private final CourseService courseService = new CourseService();

    @BeforeAll
    public static void beforeAll() {
        System.out.println("This method executed before all test methods");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("This method executed before each test methods");
    }
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        System.out.println("Test started");

        CourseService courseDAO = new CourseService();
        List<Course> courseList = courseDAO.getAllCourses();

        for(Course courses: courseList){
        System.out.println("Id# " + courses.getcId() + ")" + " " + courses.getcName()+"" + courses.getcInstructorName());
    }
}



    @AfterAll
    public static void afterAll() {
        System.out.println("This method executed after all test methods");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("This method executed after each test methods");
    }
}
