package com.example.firstproject.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String jobDescription;

    private String mode;

    private long payment;

    private String status;

    private long creditForCreate;

    private long creditForPick;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "assigned_id")
    private User assigner;

    @Column(name = "assigned_time")
    private ZonedDateTime assignerTime;

    @Column(name = "job_time")
    private ZonedDateTime jobTime;

    @Column(name = "closing_time")
    private ZonedDateTime closingTime;

    @Column(name = "added_time", nullable = false, updatable = false)
    private ZonedDateTime addedTime;

    @Column(name = "modified_time", nullable = false)
    private ZonedDateTime modifiedTime;

    @Column(name = "image_id")
    private long imageId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getAssigner() {
        return assigner;
    }

    public ZonedDateTime getAssignerTime() {
        return assignerTime;
    }

    public void setAssignerTime(ZonedDateTime assignerTime) {
        this.assignerTime = assignerTime;
    }

    public ZonedDateTime getJobTime() {
        return jobTime;
    }

    public void setJobTime(ZonedDateTime jobTime) {
        this.jobTime = jobTime;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public long getCreditForCreate() {
        return creditForCreate;
    }

    public void setCreditForCreate(long creditForCreate) {
        this.creditForCreate = creditForCreate;
    }

    public long getCreditForPick() {
        return creditForPick;
    }

    public void setCreditForPick(long creditForPick) {
        this.creditForPick = creditForPick;
    }

    public ZonedDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(ZonedDateTime closingTime) {
        this.closingTime = closingTime;
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

    public long getImageId() { return imageId; }
    public void setImageId(long imageId) {this.imageId = imageId;}

}
