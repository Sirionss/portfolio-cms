package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.model.Education;
import com.portfolio.portfolio_cms.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final EducationService service;

    public EducationController(EducationService service){ this.service = service; }
    @GetMapping
    public ResponseEntity<List<Education>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Education> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<Education> create(@RequestBody Education education) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(education));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Education> update(@PathVariable Long id, @RequestBody Education updated){
        return ResponseEntity.ok(service.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
