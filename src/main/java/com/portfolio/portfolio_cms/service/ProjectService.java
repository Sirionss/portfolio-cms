package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Project;
import com.portfolio.portfolio_cms.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAll(){
        return repository.findAll();
    }

    public Project getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Project create(Project project){
        return repository.save(project);
    }

    public Project update(Long id, Project updated){
        Project existing = getById(id);
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getGithubUrl() != null) existing.setGithubUrl(updated.getGithubUrl());
        if (updated.getFeatures() != null) existing.setFeatures(updated.getFeatures());
        if (updated.getTechnologies() != null) existing.setTechnologies(updated.getTechnologies());
        return repository.save(existing);
    }
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
