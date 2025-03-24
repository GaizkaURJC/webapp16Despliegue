package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

/**
 * This class represents an Image entity that is mapped to the "image_table" in
 * the database.
 * It uses JPA annotations to define the entity and its fields.
 * The Image class contains an ID, a Blob to store the image file, and a title
 * for the image.
 * It provides constructors for creating instances and getter methods to access
 * the fields.
 */

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
