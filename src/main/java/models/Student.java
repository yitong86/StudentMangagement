package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Student")

public class Student {
    @Id
    private String sEmail;

    private String sName;

    private  String sPass;


  @ManyToMany (cascade = CascadeType.ALL)
  @JoinTable(name = "student_courses",joinColumns = {@JoinColumn(name = "s_email")},inverseJoinColumns = {@JoinColumn(name ="c_id")})
   private List<Course> sCourses;



    public Student() {
    }

    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sPass='" + sPass + '\'' +
                ", sCourses=" + sCourses +
                '}';
    }
}
