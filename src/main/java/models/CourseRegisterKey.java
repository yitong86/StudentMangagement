package models;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable

public class CourseRegisterKey implements Serializable {

    @Column(name = "s_email")
    String sEmail;

    @Column(name = "c_id")
    int cId;

    public CourseRegisterKey() {
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "CourseRegisterKey{" +
                "sEmail='" + sEmail + '\'' +
                ", cId=" + cId +
                '}';
    }
    public void save(){

    }
}
