package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.ContactLink;
import com.portfolio.portfolio_cms.repository.ContactLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactLinkService {
    private final ContactLinkRepository repository;

    public ContactLinkService(ContactLinkRepository repository) {
        this.repository = repository;
    }

    public List<ContactLink> getAll(){
        return repository.findAll();
    }

    public ContactLink getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactLink not found with id: " + id));
    }

    public ContactLink create(ContactLink contactLink){
        return repository.save(contactLink);
    }

    public ContactLink update(Long id, ContactLink updated){
        ContactLink existing = getById(id);
        if (updated.getLabel() != null) existing.setLabel(updated.getLabel());
        if (updated.getUrl() != null) existing.setUrl(updated.getUrl());
        if (updated.getIcon() != null) existing.setIcon(updated.getIcon());
        return repository.save(existing);
    }
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("ContactLink not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
