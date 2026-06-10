package com.portfolio.portfolio_cms.service;


import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.AboutSection;
import com.portfolio.portfolio_cms.repository.AboutSectionRepository;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AboutSectionServiceTest {
   @Mock
   private AboutSectionRepository aboutSectionRepository;
   @InjectMocks
   private AboutSectionService aboutSectionService;
   private AboutSection aboutSection;

   @BeforeEach
   void setUp() {
        aboutSection = new AboutSection("Damian", "Software engineer", "Berlin", "UE","Available");
    }

   @Test
   void getAll_returnsListOfAboutSections() {
      when(aboutSectionRepository.findAll()).thenReturn(List.of(aboutSection));
      List<AboutSection> result = aboutSectionService.getAll();
      assertThat(result).hasSize(1).containsExactly(aboutSection);
      verify(aboutSectionRepository).findAll();
   }

   @Test
   void getById_nonExistingId_throwsResourceNotFoundException() {
      when(aboutSectionRepository.findById(999L)).thenReturn(Optional.empty());
      assertThatThrownBy(() -> aboutSectionService.getById(999L))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessageContaining("AboutSection not found with id: 999");
   }
   @Test
   void getById_existingId_returnsAboutSection() {
      when(aboutSectionRepository.findById(1L)).thenReturn(Optional.of(aboutSection));
      AboutSection result = aboutSectionService.getById(1L);
      assertThat(result).isEqualTo(aboutSection);
   }

   @Test
   void create_savesAndReturnsAboutSection() {
      when(aboutSectionRepository.save(aboutSection)).thenReturn(aboutSection);
      AboutSection result = aboutSectionService.create(aboutSection);
      assertThat(result).isEqualTo(aboutSection);
   }
   @Test
   void update_existingId_updatesAllProvidedFields(){
      when(aboutSectionRepository.findById(1L)).thenReturn(Optional.of(aboutSection));
      when(aboutSectionRepository.save(any())).thenReturn(aboutSection);
      AboutSection updateData = new AboutSection("Damian", "Software engineer", "Munich", "UE","Available");
      aboutSectionService.update(1L, updateData);
      assertThat(aboutSection.getLocation()).isEqualTo("Munich");
      verify(aboutSectionRepository).save(aboutSection);
   }



   @Test
   void update_existingId_nullFields_doesNotOverrideOriginal(){
      String originalName = aboutSection.getTitle();
      when(aboutSectionRepository.findById(1L)).thenReturn(Optional.of(aboutSection));
      when(aboutSectionRepository.save(any())).thenReturn(aboutSection);
      AboutSection updateData = new AboutSection();
      aboutSectionService.update(1L, updateData);
      assertThat(aboutSection.getTitle()).isEqualTo(originalName);
   }

   @Test
   void update_nonExistingId_throwsResourceNotFoundException() {
      when(aboutSectionRepository.findById(999L)).thenReturn(Optional.empty());
      assertThatThrownBy(() -> aboutSectionService.update(999L, new AboutSection()))
              .isInstanceOf(ResourceNotFoundException.class);
      verify(aboutSectionRepository, never()).save(any());
   }
   @Test
   void delete_existingId_deletesAboutSection(){
      when(aboutSectionRepository.existsById(1L)).thenReturn(true);
      aboutSectionService.delete(1L);
      verify(aboutSectionRepository).deleteById(1L);
   }
   @Test
   void delete_nonExistingId_throwsResourceNotFoundException(){
      when(aboutSectionRepository.existsById(999L)).thenReturn(false);
      assertThatThrownBy(() -> aboutSectionService.delete(999L))
              .isInstanceOf(ResourceNotFoundException.class);
      verify(aboutSectionRepository, never()).deleteById(any());
   }


}
