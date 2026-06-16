package com.portfolio.portfolio_cms.service;


import com.portfolio.portfolio_cms.exception.ResourceNotFoundException;
import com.portfolio.portfolio_cms.model.ContactLink;
import com.portfolio.portfolio_cms.repository.ContactLinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactLinkServiceTest {
    @Mock
    private ContactLinkRepository contactLinkRepository;
    @InjectMocks
    private ContactLinkService contactLinkService;
    private ContactLink githubContactLink;

    @BeforeEach
    void setUp(){
        githubContactLink = new ContactLink("Github", "https://github.com/Sirionss", "github-icon");
    }

    @Test
    void getAll_returnsListOfContactLinks(){
        when(contactLinkRepository.findAll()).thenReturn(List.of(githubContactLink));
        List<ContactLink> result = contactLinkService.getAll();
        assertThat(result).hasSize(1).containsExactly(githubContactLink);
        // Realized that verify was unnecessary
    }

    @Test
    void getById_nonExistingId_throwsResourceNotFoundException() {
        when(contactLinkRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> contactLinkService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("ContactLink not found with id: 999");
    }
    @Test
    void getById_existingId_returnsContactLink() {
        when(contactLinkRepository.findById(1L)).thenReturn(Optional.of(githubContactLink));
        ContactLink result = contactLinkService.getById(1L);
        assertThat(result).isEqualTo(githubContactLink);
    }
    @Test
    void create_savesAndReturnsContactLink(){

    }
    @Test
    void update_existingId_updatesAllProvidedFields(){

    }
    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal(){

    }
    @Test
    void update_nonExistingId_throwsResourceNotFoundException(){

    }
    @Test
    void delete_existingId_deletesContactLink(){

    }
    @Test
    void delete_nonExistingId_throwsResourceNotFoundException(){

    }

    // TODO: finish Tests
}
