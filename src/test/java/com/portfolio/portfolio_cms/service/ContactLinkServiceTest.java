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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactLinkServiceTest {
    @Mock
    private ContactLinkRepository contactLinkRepository;
    @InjectMocks
    private ContactLinkService contactLinkService;
    private ContactLink githubContactLink;
    private ContactLink linkedinContactLink;

    @BeforeEach
    void setUp(){
        githubContactLink = new ContactLink("Github", "https://github.com/Sirionss", "github-icon");
        linkedinContactLink = new ContactLink("LinkedIN","https://www.linkedin.com/in/damian-grishko", "linkedin-icon");
    }

    @Test
    void getAll_returnsListOfContactLinks(){
        when(contactLinkRepository.findAll()).thenReturn(List.of(githubContactLink,linkedinContactLink));
        List<ContactLink> result = contactLinkService.getAll();
        assertThat(result).hasSize(2).containsExactly(githubContactLink,linkedinContactLink);
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
        when(contactLinkRepository.save(githubContactLink)).thenReturn(githubContactLink);
        ContactLink result = contactLinkService.create(githubContactLink);
        assertThat(result).isEqualTo(githubContactLink);
    }
    @Test
    void update_existingId_updatesAllProvidedFields(){
        when(contactLinkRepository.findById(1L)).thenReturn(Optional.of(githubContactLink));
        when(contactLinkRepository.save(any())).thenReturn(githubContactLink);
        ContactLink updateData = new ContactLink("Github", "https://github.com/damian", "github-icon");
        contactLinkService.update(1L, updateData);
        assertThat(githubContactLink.getUrl()).isEqualTo("https://github.com/damian");
    }
    @Test
    void update_existingId_nullFields_doesNotOverrideOriginal(){
        String originalUrl = githubContactLink.getUrl();
        when(contactLinkRepository.findById(1L)).thenReturn(Optional.of(githubContactLink));
        when(contactLinkRepository.save(any())).thenReturn(githubContactLink);
        ContactLink updateData = new ContactLink();
        contactLinkService.update(1L, updateData);
        assertThat(githubContactLink.getUrl()).isEqualTo(originalUrl);
    }
    @Test
    void update_nonExistingId_throwsResourceNotFoundException(){
        when(contactLinkRepository.findById(999L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> contactLinkService.update(999L, new ContactLink()))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(contactLinkRepository, never()).save(any());
    }
    @Test
    void delete_existingId_deletesContactLink(){
        when(contactLinkRepository.existsById(1L)).thenReturn(true);
        contactLinkService.delete(1L);
        verify(contactLinkRepository).deleteById(1L);
    }
    @Test
    void delete_nonExistingId_throwsResourceNotFoundException(){
        when(contactLinkRepository.existsById(999L)).thenReturn(false);
        assertThatThrownBy(() -> contactLinkService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(contactLinkRepository, never()).deleteById(any());
    }
}
