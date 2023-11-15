package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.AccountRepository;
import com.example.eindopdracht.repositories.ViewingRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ViewingServiceTest {

    @Mock
    private ViewingRepository viewingRepository;

    @InjectMocks
    private ViewingService viewingService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void testShouldGetAllViewings() {
        // arrange - creating/adding a new viewing
        Viewing viewing1 = new Viewing();
        viewing1.setFullname("Jan Jansen");
        viewing1.setPhonenumber("0611122333");
        viewing1.setEmail("jan@jansen.com");

        Viewing viewing2 = new Viewing();
        viewing2.setFullname("Jannie de Boer");
        viewing2.setPhonenumber("0611122333");
        viewing2.setEmail("jannie@deboer.com");

        Account account1 = new Account();
        account1.setAccountId(123L);

        List<Viewing> viewings = new ArrayList<>();
        viewings.add(viewing1);
        viewings.add(viewing2);
        viewing1.setAccount(account1);
        viewing2.setAccount(account1);

        Mockito.when(viewingRepository.findAll()).thenReturn(viewings);

        // act
        List<ViewingDto> result = viewingService.getAllViewings();

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void testShouldCreateViewing() {
        // arrange
        Viewing newViewing = new Viewing();
        newViewing.setFullname("Jan Jansen");
        newViewing.setPhonenumber("0611122333");
        newViewing.setEmail("jan@jansen.com");

        Account newAccount = new Account();
        newAccount.setAccountId(123L);
        newViewing.setAccount(newAccount);

        Mockito.when(viewingRepository.save(Mockito.any(Viewing.class))).thenReturn(newViewing);

        Mockito.when(accountRepository.findById(newAccount.getAccountId())).thenReturn(Optional.of(newAccount));

        // act
        ViewingDto savedViewingDto = viewingService.createViewing(new ViewingDto(), newAccount.getAccountId());

        // assert
        assertEquals("Jan Jansen", savedViewingDto.getFullname());
        assertEquals("0611122333", savedViewingDto.getPhonenumber());
        assertEquals("jan@jansen.com", savedViewingDto.getEmail());
    }

    @Test
    void testShouldDeleteViewing() {
        // Arrange
        Long viewingId = 1L;

        Mockito.doNothing().when(viewingRepository).deleteById(viewingId);

        // Act
        String result = viewingService.deleteViewing(viewingId);

        // Assert
        // Verify that the deleteById method of viewingRepository is called with the correct ID
        Mockito.verify(viewingRepository).deleteById(viewingId);

        // Verify the returned message
        assertEquals("Viewing successfully deleted", result);
    }
}
