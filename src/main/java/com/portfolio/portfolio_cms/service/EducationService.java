package com.portfolio.portfolio_cms.service;


import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Education;
import com.portfolio.portfolio_cms.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EducationService {
    private final EducationRepository repository;

    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public List<Education> getAll(){
        return repository.findAll();
    }

    public Education getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));
    }

    public Education create(Education education){
        return repository.save(education);
    }

    public Education update(Long id, Education updated){
        Education existing = getById(id);
        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getSchool() != null) existing.setSchool(updated.getSchool());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getDateRange() != null) existing.setDateRange(updated.getDateRange());
        return repository.save(existing);
    }
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Education not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
