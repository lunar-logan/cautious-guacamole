package com.anurag.ams.base.common;

import com.anurag.ams.base.common.type.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * Encapsulates basic personal details of a human being
 *
 * @author Anurag Gautam
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -175960846245047955L;

    private String firstName;
    private String middleName;
    private String lastName;

    private Date birthday;
    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
