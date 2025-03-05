package com.daw.daw.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

@Entity(name = "image_table")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private Blob imageFile;

    private String title;

    public Image() {
    }

    public Image(java.sql.Blob imageFile, String title) {
        this.imageFile = imageFile;
        this.title = title;
    }

    public void getId(Long id) {
        this.id = id;
    }

    public Blob getImageFile() {
        return imageFile;
    }

}
