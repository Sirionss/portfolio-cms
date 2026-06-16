package com.portfolio.portfolio_cms.service;

import com.portfolio.portfolio_cms.model.Education;
import com.portfolio.portfolio_cms.repository.EducationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
    @Mock
    private EducationRepository educationRepository;
    @InjectMocks
    private EducationService educationService;
    private Education univercityEducation;
    private Education specialClassEducation;

    @BeforeEach
    void setUp(){
        univercityEducation = new Education("Software-engineer","UE","2025-2027","Bachelors degree in software-engineering");
        specialClassEducation = new Education("Java", "VK Education","2027", "Spring boot, mockito and etc");
    }

    // TODO: finish tests
}
