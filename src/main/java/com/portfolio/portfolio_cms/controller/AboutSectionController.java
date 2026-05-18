package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.model.AboutSection;
import com.portfolio.portfolio_cms.service.AboutSectionService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/about")
public class AboutSectionController {
    private final AboutSectionService service;

    AboutSectionController(AboutSectionService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<AboutSection>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AboutSection> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<AboutSection> create(@RequestBody AboutSection aboutSection) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(aboutSection));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
