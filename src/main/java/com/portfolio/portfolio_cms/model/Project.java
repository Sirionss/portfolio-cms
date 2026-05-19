package com.portfolio.portfolio_cms.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String githubUrl;
    @ElementCollection
    @CollectionTable(
            name = "project_features",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "feature", columnDefinition = "TEXT")
    private List<String> features = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
            name = "project_technologies",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "technology", columnDefinition = "TEXT")
    private List<String> technologies =  new ArrayList<>();

    public Project() {}
    public Project(String name,String description,String githubUrl,
                   List<String> features,List<String> technologies) {
        this.name = name;
        this.description = description;
        this.githubUrl = githubUrl;
        this.features = features;
        this.technologies = technologies;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
}
