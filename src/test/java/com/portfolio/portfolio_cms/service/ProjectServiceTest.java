package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.model.Project;
import com.portfolio.portfolio_cms.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        
    }

    @Test
    void getById_existingId_returnsProject() {

    }

    @Test
    void getById_nonExistingId_throwsResourceNotFoundException() {

    }

    @Test
    void create_savesAndReturnsProject() {

    }

    @Test
    void update_existingId_updatesAllProvidedFields() {

    }

    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal() {

    }

    @Test
    void update_nonExistingId_throwsResourceNotFoundException() {

    }

    @Test
    void delete_existingId_deletesProject() {

    }

    @Test
    void delete_nonExistingId_throwsResourceNotFoundException() {

    }
}
