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

   // @ManyToMany(targetEntity = Course.class)
  @ManyToMany (cascade = CascadeType.ALL)
   private List<Course> sCourses;



    public Student() {
    }

    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }

    public Student(String sName, String sEmail, String sPass, List<Course> sCourses) {
        this.sName = sName;
        this.sEmail = sEmail;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
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

public void addCourse(Course course){
        this.sCourses.add(course);
}

}
