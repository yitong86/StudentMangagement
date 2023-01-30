package Runner;

import models.Course;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Access {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        //create course/class  entity set one
        System.out.println("----------Inserting student record----------");
        Course c1 = new Course(1, "English", "Anderea Scamaden");
        Course c2 = new Course(2, "Mathematics", "Eustace Niemetz");
        Course c3 = new Course(3, "Anatomy", "Anderea Scamaden");
        Course c4 = new Course(4, "Organic Chemistry", "Odessa Belcher");
        Course c5 = new Course(5, "Physics", "Dani Swallow");
        Course c6 = new Course(6, "Digital Logic", "Glenden Reilingen");
        Course c7 = new Course(7, "Object Oriented Programming", "Giselle Ardy");
        Course c8 = new Course(8, "Data Structures", "Carolan Stoller");
        Course c9 = new Course(9, "Politics", "Carmita De Maine");
        Course c10 = new Course(10, "Art", "Kingsly Doxsey");

        //store course/class
        session.save(c1);
        session.save(c2);
        session.save(c3);
        session.save(c4);
        session.save(c5);
        session.save(c6);
        session.save(c7);
        session.save(c8);
        session.save(c9);
        session.save(c10);
        //create course one/class one
        List<Course> CourseList1 = new ArrayList<>();

        CourseList1.add(c1);
        CourseList1.add(c2);
        CourseList1.add(c3);
        CourseList1.add(c4);
        CourseList1.add(c5);
        CourseList1.add(c6);
        CourseList1.add(c7);
        CourseList1.add(c8);
        CourseList1.add(c9);
        CourseList1.add(c10);

        Student s1 = new Student();
        s1.setsEmail("hluckham0@google.ru");
        s1.setsName("Hazel Luckham");
        s1.setsPass("X1uZcoIh0dj");


        Student s2 = new Student();
        s2.setsEmail("sbowden1@yellowbook.com");
        s2.setsName("Sonnnie Bowden");
        s2.setsPass("SJc4aWSU");


        Student s3 = new Student();
        s3.setsEmail("qllorens2@howstuffworks.com");
        s3.setsName("Quillan Llorens");
        s3.setsPass("W6rJuxd");


        Student s4 = new Student();
        s4.setsEmail("cstartin3@flickr.com");
        s4.setsName("Clem Startin");
        s4.setsPass("XYHzJ1S");


        Student s5 = new Student();
        s5.setsEmail("tattwool4@biglobe.ne.jp");
        s5.setsName("Thornie Attwool");
        s5.setsPass("Hjt0SoVmuBz");


        Student s6 = new Student();
        s6.setsEmail("hguerre5@deviantart.com");
        s6.setsName("Harcourt Guerre");
        s6.setsPass("OzcxzD1PGs");


        Student s7 = new Student();
        s7.setsEmail("htaffley6@columbia.edu");
        s7.setsName("Holmes Taffley");
        s7.setsPass("xowtOQ");


        Student s8 = new Student();
        s8.setsEmail("aiannitti7@is.gd");
        s8.setsName("Alexandra Iannitti");
        s8.setsPass("TWP4hf5j");


        Student s9 = new Student();
        s9.setsEmail("ljiroudek8@sitemeter.com");
        s9.setsName("Laryssa Jiroudek");
        s9.setsPass("bXRoLUP");


        Student s10 = new Student();
        s10.setsEmail("cjaulme9@bing.com");
        s10.setsName("Cahra Jaulme");
        s10.setsPass("FnVklVgC6r6");
        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);
        session.save(s5);
        session.save(s6);
        session.save(s7);
        session.save(s8);
        session.save(s9);
        session.save(s10);
List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        studentList.add(s6);
        studentList.add(s7);
        studentList.add(s8);
        studentList.add(s9);
        studentList.add(s10);



        t.commit();
        factory.close();
        session.close();
    }
}


