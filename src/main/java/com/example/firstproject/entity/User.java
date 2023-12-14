package com.example.firstproject.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Email
    private String email;

    private String password;

    private String phoneNumber;

    private String status;

    private String role;

    @Column(name = "image_id")
    private long imageId;

    @Column(name = "added_time", nullable = false, updatable = false)
    private ZonedDateTime addedTime;

    @Column(name = "modified_time", nullable = false)
    private ZonedDateTime modifiedTime;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ZonedDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(ZonedDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public ZonedDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(ZonedDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
