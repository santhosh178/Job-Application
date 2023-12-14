package com.example.firstproject.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "recharge")
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "added_time", nullable = false, updatable = false)
    private ZonedDateTime addedTime;

    @Column(name = "modified_time", nullable = false)
    private ZonedDateTime modifiedTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

}
