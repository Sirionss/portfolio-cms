package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.Project;
import com.portfolio.portfolio_cms.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;
    private Project taskflowProject;

    @BeforeEach
    void setUp() {
        taskflowProject =  new Project(
                "TaskFlow",
                "Task management REST API",
                "https://github.com/Sirionss/taskflow",
                List.of("CRUD", "JWT auth", "Role-based access"),
                List.of("Java 17", "Spring Boot", "PostgreSQL")
        );
    }



    @Test
    void getAll_returnsListOfProjects() {
        when(projectRepository.findAll()).thenReturn(List.of(taskflowProject));
        List<Project> result = projectService.getAll();
        assertThat(result).hasSize(1).containsExactly(taskflowProject);
    }

    @Test
    void getById_existingId_returnsProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(taskflowProject));
        Project result = projectService.getById(1L);
        assertThat(result).isEqualTo(taskflowProject);
    }

    @Test
    void getById_nonExistingId_throwsResourceNotFoundException() {
        when(projectRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> projectService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Project not found with id: 999");
    }

    @Test
    void create_savesAndReturnsProject() {
        when((projectRepository.save(taskflowProject))).thenReturn(taskflowProject);
        Project result = projectService.create(taskflowProject);
        assertThat(result).isEqualTo(taskflowProject);
    }

    @Test
    void update_existingId_updatesAllProvidedFields() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(taskflowProject));
        when(projectRepository.save(any())).thenReturn(taskflowProject);
        Project updateData = new Project(
                "FlowTask",
                "Task management REST API",
                "https://github.com/Sirionss/taskflow",
                List.of("CRUD", "JWT auth", "Role-based access"),
                List.of("Java 17", "Spring Boot", "PostgreSQL")
        );
        projectService.update(1L, updateData);
        assertThat(taskflowProject.getName()).isEqualTo("FlowTask");
        verify(projectRepository).save(taskflowProject);
    }

    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal() {
        String originalName = taskflowProject.getName();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(taskflowProject));
        when(projectRepository.save(any())).thenReturn(taskflowProject);
        Project updateData = new Project();
        projectService.update(1L,updateData);
        assertThat(taskflowProject.getName()).isEqualTo(originalName);
    }

    @Test
    void update_nonExistingId_throwsResourceNotFoundException() {
        when(projectRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> projectService.update(999L, new Project()))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(projectRepository, never()).save(any());
    }

    @Test
    void delete_existingId_deletesProject() {
        when(projectRepository.existsById(1L)).thenReturn(true);
        projectService.delete(1L);
        verify(projectRepository).deleteById(1L);
    }

    @Test
    void delete_nonExistingId_throwsResourceNotFoundException() {
        when(projectRepository.existsById(999L)).thenReturn(false);
        assertThatThrownBy(() -> projectService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(projectRepository, never()).deleteById(any());
    }
}
