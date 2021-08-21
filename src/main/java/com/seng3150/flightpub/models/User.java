/*
    User.java
        - Used to model the database user_account table
        - Mapping is used by the @annotations
 */

package com.seng3150.flightpub.models;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_account")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User() {

    }

    public User(String userName, String firstName, String lastName, String address, String phoneNumber, String password, byte[] salt) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salt = salt;
        this.passwordHash = password;
        this.userType = "user";
    }

    @Id
    @JsonIgnore
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "salt", nullable = false)
    private byte[] salt;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "last_location")
    private String lastLocation;

    @Column(name = "account_type")
    private String userType;

    @Column(name = "requesting")
    private int requesting;

    @Column(name = "why")
    private String why;

    @Column(name = "referencing")
    private String referencing;

    @Column(name = "experience")
    private String experience;

    @Column(name = "requesting_for")
    private String requestingFor;

//    @OneToMany(fetch = FetchType.LAZY)
//    @Column(name = "booking_list")
//    private List<Booking> bookingList;

    public User(String user_name, String first_name, String last_name, String phone_number) {
    }

    public long getUserId() { return id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {return userType; }

    public void setAddress(String address) {
        this.address = address;
    }
}
