package com.example.firstproject.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String imageName;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
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
