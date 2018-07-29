package com.Thorn.model;


import org.springframework.format.annotation.DateTimeFormat;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.Size;
import java.util.Date;

public class UserTest {
    // @Min(value = 18,message = "sss")
    private String name;
    // @Size(min=2, max=20)
    private String password;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthdate;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
