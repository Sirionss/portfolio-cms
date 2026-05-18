package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.AboutSection;
import com.portfolio.portfolio_cms.repository.AboutSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutSectionService {
    private final AboutSectionRepository repository;

    public AboutSectionService(AboutSectionRepository repository){
        this.repository = repository;
    }

    public List<AboutSection> getAll(){
        return repository.findAll();
    }

    public AboutSection getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AboutSection not found with id: " + id));
    }

    public AboutSection create(AboutSection aboutSection){
        return repository.save(aboutSection);
    }

    public AboutSection update(Long id, AboutSection updated){
        AboutSection existing = getById(id);
        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
        if (updated.getUniversity() != null) existing.setUniversity(updated.getUniversity());
        if (updated.getAvailability() != null) existing.setAvailability(updated.getAvailability());
        return repository.save(existing);
    }
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("AboutSection not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
