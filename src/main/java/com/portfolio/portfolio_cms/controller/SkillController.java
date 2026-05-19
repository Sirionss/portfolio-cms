package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.model.Skill;
import com.portfolio.portfolio_cms.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService service;

    public SkillController(SkillService service){ this.service = service; }
    @GetMapping
    public ResponseEntity<List<Skill>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(skill));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill updated){
        return ResponseEntity.ok(service.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
