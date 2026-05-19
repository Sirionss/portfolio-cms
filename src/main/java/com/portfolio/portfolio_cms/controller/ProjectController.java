package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.model.Project;
import com.portfolio.portfolio_cms.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project updated){
        return ResponseEntity.ok(service.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
