package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Skill;
import com.portfolio.portfolio_cms.model.SkillCategory;
import com.portfolio.portfolio_cms.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private SkillService skillService;
    private Skill javaSkill;
    private Skill pythonSkill;

    @BeforeEach
    void setUp() {
        javaSkill = new Skill();
        javaSkill.setName("Java");
        javaSkill.setIcon("java-icon");
        javaSkill.setDetail("Backend development");
        javaSkill.setCategory(SkillCategory.BACKEND);

        pythonSkill = new Skill();
        pythonSkill.setName("Python");
        pythonSkill.setIcon("python-icon");
        pythonSkill.setDetail("Scripting");
        pythonSkill.setCategory(SkillCategory.BACKEND);
    }

    @Test
    void getAll_returnsListOfSkills() {
        when(skillRepository.findAll()).thenReturn(List.of(javaSkill, pythonSkill));
        List<Skill> result = skillService.getAll();
        assertThat(result).hasSize(2).containsExactly(javaSkill, pythonSkill);
        verify(skillRepository).findAll();
    }

    @Test
    void getById_nonExistingId_throwsResourceNotFoundException() {
        when(skillRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> skillService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Skill not found with id: 999");
    }

    @Test
    void getById_existingId_returnsSkill() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(javaSkill));
        Skill result = skillService.getById(1L);
        assertThat(result).isEqualTo(javaSkill);
    }

    @Test
    void create_savesAndReturnsSkill() {
        when(skillRepository.save(javaSkill)).thenReturn(javaSkill);
        Skill result = skillService.create(javaSkill);
        assertThat(result).isEqualTo(javaSkill);
    }
    @Test
    void update_existingId_updatesAllProvidedFields(){
        when(skillRepository.findById(1L)).thenReturn(Optional.of(javaSkill));
        when(skillRepository.save(any())).thenReturn(javaSkill);
        Skill updateData = new Skill();
        updateData.setName("Java 17");
        updateData.setIcon("java-icon");
        updateData.setDetail("Backend development");
        updateData.setCategory(SkillCategory.BACKEND);
        skillService.update(1L, updateData);
        assertThat(javaSkill.getName()).isEqualTo("Java 17");
        verify(skillRepository).save(javaSkill);
    }

    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal(){
        String originalName = javaSkill.getName();
        when(skillRepository.findById(1L)).thenReturn(Optional.of(javaSkill));
        when(skillRepository.save(any())).thenReturn(javaSkill);
        Skill updateData = new Skill();
        skillService.update(1L, updateData);
        assertThat(javaSkill.getName()).isEqualTo(originalName);
    }

    @Test
    void update_nonExistingId_throwsResourceNotFoundException() {
        when(skillRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> skillService.update(999L, new Skill()))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(skillRepository, never()).save(any());
    }
    @Test
    void delete_existingId_deletesSkill(){
        when(skillRepository.existsById(1L)).thenReturn(true);
        skillService.delete(1L);
        verify(skillRepository).deleteById(1L);
    }
    @Test
    void delete_nonExistingId_throwsResourceNotFoundException(){
        when(skillRepository.existsById(999L)).thenReturn(false);
        assertThatThrownBy(() -> skillService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(skillRepository, never()).deleteById(any());
    }
}