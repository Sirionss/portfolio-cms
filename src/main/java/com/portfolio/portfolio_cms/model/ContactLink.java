package com.portfolio.portfolio_cms.model;

import jakarta.persistence.*;
@Entity
@Table(name = "contact_links")
public class ContactLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String label;
    @Column(nullable = false, length = 500)
    private String url;
    @Column(length = 100)
    private String icon;

    public ContactLink() {}
    public ContactLink(String label,String url,String icon){
        this.label = label;
        this.url = url;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
