package com.portfolio.portfolio_cms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "about_section")
public class AboutSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 100)
    private String location;
    @Column(length = 200)
    private String university;
    @Column(length = 200)
    private String availability;

    // Constructor for JPA
    public AboutSection(){}

    public AboutSection(String title, String description, String location, String university, String availability){
        this.title = title;
        this.description = description;
        this.location = location;
        this.university = university;
        this.availability = availability;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




}
