package com.example.firstproject.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "credit",uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private long credit;

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

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
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
