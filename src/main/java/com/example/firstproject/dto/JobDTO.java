package com.example.firstproject.dto;

import com.example.firstproject.entity.Category;

import java.time.ZonedDateTime;

public class JobDTO {

    private long id;
    private String jobDescription;

    private UserDTO user;

    private AddressDTO address;

    private Category category;

    private String mode;

    private ZonedDateTime jobTime;

    private long imageId;

    private long payment;

    private String status;

    private UserDTO assigner;

    private ZonedDateTime addedTime;

    private ZonedDateTime assignedTime;

    private ZonedDateTime closingTime;

    private ZonedDateTime modifiedTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ZonedDateTime getJobTime() {
        return jobTime;
    }

    public void setJobTime(ZonedDateTime jobTime) {
        this.jobTime = jobTime;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    public UserDTO getAssigner() {
        return assigner;
    }

    public void setAssigner(UserDTO assigner) {
        this.assigner = assigner;
    }

    public ZonedDateTime getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(ZonedDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public ZonedDateTime getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(ZonedDateTime assignedTime) {
        this.assignedTime = assignedTime;
    }

    public ZonedDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(ZonedDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public ZonedDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(ZonedDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
