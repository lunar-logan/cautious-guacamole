package com.anurag.ams.base.request;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public class CreatePlayerRequest implements Serializable {
    private static final long serialVersionUID = 8203875649827440733L;

    private String firstName;
    private String lastName;
    private Date birthday;
    private Character gender;
    private String email;
    private String handle;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toString() {
        return "CreatePlayerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", handle='" + handle + '\'' +
                '}';
    }
}
