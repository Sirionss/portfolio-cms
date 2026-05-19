package com.portfolio.portfolio_cms.model;

import jakarta.persistence.*;


@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 200)
    private String school;
    @Column(length = 50)
    private String dateRange;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Education(){}
    public Education(String title, String school, String dateRange, String description){
        this.title =title;
        this.school = school;
        this.dateRange = dateRange;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
