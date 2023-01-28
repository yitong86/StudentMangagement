package Runner;

import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class AccessCourse {

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


        t.commit();

    }
}
