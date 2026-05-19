package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.model.ContactLink;
import com.portfolio.portfolio_cms.service.ContactLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/contacts")
public class ContactLinkController {
    private final ContactLinkService service;

    public ContactLinkController(ContactLinkService service){ this.service = service; }
    @GetMapping
    public ResponseEntity<List<ContactLink>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactLink> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<ContactLink> create(@RequestBody ContactLink contactLink) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(contactLink));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactLink> update(@PathVariable Long id, @RequestBody ContactLink updated){
        return ResponseEntity.ok(service.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
