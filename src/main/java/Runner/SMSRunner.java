package Runner;

import Services.CourseService;
import Services.StudentService;
import jpa.dao.StudentDao;
import models.Course;
import models.Student;

import static java.lang.System.out;

import java.sql.SQLException;
import java.util.*;


public class SMSRunner {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Are you a(n)");
        System.out.println("1. Student");
        System.out.println("2. Quit");
        System.out.println("Please, enter 1 or 2. ");
        Scanner in = new Scanner(System.in);

    int answer = in.nextInt();

        if (answer == 1){

       StudentService studentDAO = new StudentService();
        List<Student> studentList = studentDAO.getAllStudents();


        System.out.println("Enter your email: ");
        in.nextLine();
        String email = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();

        if (studentDAO.validateStudent(studentList, email, password)) {
            CourseService courseDAO = new CourseService();
         //   AttendingDAO attendingDAO = new AttendingDAO();
            Student student = studentDAO.getStudentByEmail(studentList, email);
            List<Course> courseList = courseDAO.getAllCourses();
           // List<Attending> attendingList = attendingDAO.getAttending();

          //  myClasses(student, courseList, attendingList);


            System.out.println("1. Register to Class");
            System.out.println("2. LogOut");
            System.out.print("Answer: \n");
            answer = in.nextInt();

            if (answer == 1) {
                //Display all courses

                allCourses(courseList);
                System.out.println("Which Course?");

                int courseID = in.nextInt();

             //   courseDAO.getCourseById(courseID);
        studentDAO.getStudentCourses(courseID);
                myClasses(student,courseList);
//                CourseService cs = new CourseService();
//                Course course = cs.getAllCourses().get(courseID);
//                courseList.add(course);
//                myClasses(student,courseList);
            }

            System.out.println("You have been signed out.\n" +
                    "\n" +
                    " ");
        }
        else {
            System.out.println("Invalid Email or Password.");
        }
    }

        System.out.println("Goodbye.");
}

    public static void myClasses (Student student, List<Course> courseList) throws SQLException, ClassNotFoundException {

        System.out.println("My Classes: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "COURSE NAME", "INSTRUCTOR NAME \n");
       // AttendingDAO attendingDAO = new AttendingDAO();
       // List<Course> courses = attendingDAO.getStudentCourses(courseList, attendingList, student.getEmail());
        StudentService studentDAO = new StudentService();
        CourseService coursesDAO = new CourseService();
      //  student = studentDAO.getStudentCourses();
        List<Course> courses = coursesDAO.getAllCourses();

        for(Course course : courses) {
            System.out.printf("%-5s|%-25s|%-25s\n", course.getcId(),course.getcName(),course.getcInstructorName());

        }
    }

    public static void allCourses (List<Course> courseList) {

        System.out.println("All course: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "COURSE NAME", "INSTRUCTOR NAME \n");
        for(Course course : courseList){
            System.out.printf("%-5s|%-25s|%-25s\n", course.getcId(),course.getcName(),course.getcInstructorName());

        }
    }
}