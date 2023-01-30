package Runner;

import Services.CourseService;
import Services.StudentService;
import jpa.dao.StudentDao;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static java.lang.System.out;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.*;


public class SMSRunner {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Are you a(n)");
        System.out.println("1. Student");
        System.out.println("2. Quit");
        System.out.println("Please, enter 1 or 2. ");
        Scanner scanner = new Scanner(System.in);

    int ans = scanner.nextInt();

        if (ans == 1){

       StudentService studentDAO = new StudentService();

        List<Student> studentList = studentDAO.getAllStudents();


        System.out.println("Enter your email: ");
        scanner.nextLine();
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

          Student  currentStudent = new Student();
        if (studentDAO.validateStudent(studentList, email, password)) {

            for(int i = 0;i<studentList.size();i++){
               if (studentList.get(i).getsEmail().equals(email)){
                  currentStudent = studentList.get(i);
               }
            }

          //current student info
         //   out.println(currentStudent.toString());
            //Student{sEmail='aiannitti7@is.gd', sName='Alexandra Iannitti', sPass='TWP4hf5j', sCourses=null}

            System.out.println("1. Register to Class");
            System.out.println("2. LogOut");
            System.out.println("Please, enter 1 or 2. " );
            ans = scanner.nextInt();

            if (ans == 1) {
                //Display all courses
                CourseService courseDAO = new CourseService();
                List<Course> courseList = courseDAO.getAllCourses();

                allCourses(courseList);
                System.out.println("Which Course?");

                int courseID = scanner.nextInt();
                List<Course> newList = new ArrayList<>();
                for (int i = 0; i < courseList.size(); i++) {
                    if (courseList.get(i).getcId() == courseID) {
                        Course regiCourse = courseList.get(i);
                        out.println(regiCourse);
                        newList.add(regiCourse);

                    }

                }
                myClasses(currentStudent, newList);

                StudentCoursesID s = new StudentCoursesID();
                List<StudentCoursesID> sc = new ArrayList<>();
                s.seteMail(email);
                s.setCourseID(courseID);
                out.println(s);
                //StudentCoursesID{eMail='aiannitti7@is.gd', courseID=1}
                Course newC = courseDAO.getAllCourses().get(0);
                if(newC != null) {
                    studentDAO.registerStudentToCourse(sc, currentStudent.getsEmail(), courseID);
                    Student temp = studentDAO.getStudentByEmail(currentStudent.getsEmail());
                    out.println(temp);

                    List<Course> sCourse = studentDAO.getStudentCourses(sc, email, newList);
                    courseList.removeAll(sCourse);
                }

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
        out.println();
        System.out.printf("%-5s|%-25s|%-25s", "#", "Course Name", "Instructor Name \n");
        out.println();
        for(Course course : courseList){
            System.out.printf("%-5s|%-25s|%-25s\n", course.getcId(),course.getcName(),course.getcInstructorName());
            out.println();
        }
    }
}