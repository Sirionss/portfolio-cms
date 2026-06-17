package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Education;
import com.portfolio.portfolio_cms.repository.EducationRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
    @Mock
    private EducationRepository educationRepository;
    @InjectMocks
    private EducationService educationService;
    private Education universityEducation;
    private Education specialClassEducation;

    @BeforeEach
    void setUp(){
        universityEducation = new Education("Software-engineer","UE","2025-2027","Bachelors degree in software-engineering");
        specialClassEducation = new Education("Java", "VK Education","2027", "Spring boot, mockito and etc");
    }

    @Test
    void getAll_returnsListOfEducations() {
        when(educationRepository.findAll()).thenReturn(List.of(universityEducation,specialClassEducation));
        List<Education> result = educationService.getAll();
        assertThat(result).hasSize(2).containsExactly(universityEducation,specialClassEducation);
    }

    @Test
    void getById_existingId_returnsEducation() {
        when(educationRepository.findById(1L)).thenReturn(Optional.of(universityEducation));
        Education result = educationService.getById(1L);
        assertThat(result).isEqualTo(universityEducation);
    }

    @Test
    void getById_nonExistingId_throwsResourceNotFoundException() {
        when(educationRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> educationService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Education not found with id: 999");
    }

    @Test
    void create_savesAndReturnsEducation() {
        when(educationRepository.save(universityEducation)).thenReturn(universityEducation);
        Education result = educationService.create(universityEducation);
        assertThat(result).isEqualTo(universityEducation);
    }

    @Test
    void update_existingId_updatesAllProvidedFields() {
        when(educationRepository.findById(1L)).thenReturn(Optional.of(universityEducation));
        when(educationRepository.save(any())).thenReturn(universityEducation);
        Education updateData = new Education("Software-engineer","University of Europe","2025-2027","Bachelors degree in software-engineering");
        educationService.update(1L, updateData);
        assertThat(universityEducation.getSchool()).isEqualTo("University of Europe");
    }

    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal() {
        String originalSchool = universityEducation.getSchool();
        when(educationRepository.findById(1L)).thenReturn(Optional.of(universityEducation));
        when(educationRepository.save(any())).thenReturn(universityEducation);
        Education updateData = new Education();
        educationService.update(1L, updateData);
        assertThat(universityEducation.getSchool()).isEqualTo(originalSchool);
    }

    @Test
    void update_nonExistingId_throwsResourceNotFoundException() {
        when(educationRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> educationService.update(999L, new Education()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Education not found with id: 999");
    }

    @Test
    void delete_existingId_deletesEducation() {
        when(educationRepository.existsById(1L)).thenReturn(true);
        educationService.delete(1L);
        verify(educationRepository).deleteById(1L);
    }

    @Test
    void delete_nonExistingId_throwsResourceNotFoundException() {
        when(educationRepository.existsById(999L)).thenReturn(false);
        assertThatThrownBy(() -> educationService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Education not found with id: 999");
    }
}
