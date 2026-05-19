package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Skill;
import com.portfolio.portfolio_cms.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> getAll(){
        return repository.findAll();
    }

    public Skill getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

    public Skill create(Skill skill){
        return repository.save(skill);
    }

    public Skill update(Long id, Skill updated){
        Skill existing = getById(id);
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getIcon() != null) existing.setIcon(updated.getIcon());
        if (updated.getDetail() != null) existing.setDetail(updated.getDetail());
        if (updated.getCategory() != null) existing.setCategory(updated.getCategory());
        return repository.save(existing);
    }
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
