package Runner;

import Services.CourseService;
import Services.StudentService;
import jpa.dao.StudentDao;
import models.Course;
import models.CourseRegisterKey;
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
          Student student = studentDAO.getStudentByEmail(studentList, email);

            System.out.println("1. Register to Class");
            System.out.println("2. LogOut");
            System.out.println("Please, enter 1 or 2. " );
            answer = in.nextInt();

            if (answer == 1) {
                //Display all courses
                CourseService courseDAO = new CourseService();
                List<Course> courseList = courseDAO.getAllCourses();
                allCourses(courseList);
                System.out.println("Which Course?");

                int courseID = in.nextInt();
                List<Course> newList = new ArrayList<>();
                for(int i = 0;i<courseList.size();i++){
                    if(courseList.get(i).getcId()== courseID){
                     Course regiCourse = courseList.get(i);
                      //  out.println(regiCourse);
                        newList.add(regiCourse);


                    }

                }
                //out.println(Arrays.toString(newList.toArray()));
                List<CourseRegisterKey> courseRegisterKeys = new ArrayList<>();
                studentDAO.registerStudentToCourse(courseRegisterKeys,email,courseID);
                myClasses(student,newList);
            }

            System.out.println("You have been signed out.\n" +
                    "\n" +
                    " ");
        }
        else {
            System.out.println("Invalid Email or Password.");
        }
    }

        System.out.println("Thank you for your register. Goodbye.");
}

    public static void myClasses (Student student, List<Course> courseList) {

        System.out.println("My Classes: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "Course Name", "Instructor Name \n");
        out.println();

        for(Course course : courseList) {
            System.out.printf("%-5s|%-25s|%-25s\n", course.getcId(),course.getcName(),course.getcInstructorName());
        }
    }

    public static void allCourses (List<Course> courseList) {

        System.out.println("All course: ");
        System.out.printf("%-5s|%-25s|%-25s", "#", "Course Name", "Instructor Name \n");
        out.println();
        for(Course course : courseList){
            System.out.printf("%-5s|%-25s|%-25s\n", course.getcId(),course.getcName(),course.getcInstructorName());

        }
    }
}