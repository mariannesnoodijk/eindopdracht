package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Property;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.AccountRepository;
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
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

//    @Test
//    void testValidateEmailPattern() {
//        // Arrange
//        String validEmail = "example@example.com";
//        String invalidEmail = "invalid.email";
//
//        // Act and Assert
//        assertTrue(accountService.validateEmailPattern(validEmail));
//        assertFalse(accountService.validateEmailPattern(invalidEmail));
//    }

    @Test
    void getAllAccounts() {
        // arrange - creating/adding a new viewing
        Account account1 = new Account();
        account1.setFirstname("Jan");
        account1.setLastname("Jansen");
        account1.setPhonenumber("0611122333");
        account1.setEmailaddress("jan@jansen.com");

        Account account2 = new Account();
        account2.setFirstname("Piet");
        account2.setLastname("Pietje");
        account2.setPhonenumber("0611122333");
        account2.setEmailaddress("piet@pietje.com");

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        Mockito.when(accountRepository.findAll()).thenReturn(accounts);

        // act
        List<AccountDto> result = accountService.getAllAccounts();

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void getAccount() {
        // Arrange - creating/adding a new property
        Long accountId = 1L;
        Account account = new Account();

        account.setFirstname("Piet");
        account.setLastname("Pietje");
        account.setPhonenumber("0611122333");
        account.setEmailaddress("piet@pietje.com");

        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        AccountDto accountDto = accountService.getAccount(accountId);

        // Assert
        assertEquals("Piet", accountDto.getFirstname());
        assertEquals("Pietje", accountDto.getLastname());
        assertEquals("0611122333", accountDto.getPhonenumber());
        assertEquals("piet@pietje.com", accountDto.getEmailaddress());
    }

    @Test
    void getAccountWithInvalidId() {
        // Arrange - creating/adding a new property
        Long accountId = 1L;
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act and Assert
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            accountService.getAccount(accountId);
        });

        assertEquals("Account not found with ID: " + accountId, exception.getMessage());
    }

    @Test
    void createAccount() {

        // arrange
        Account newAccount = new Account();
        newAccount.setFirstname("Piet");
        newAccount.setLastname("Pietje");
        newAccount.setPhonenumber("0611122333");
        newAccount.setEmailaddress("piet@pietje.com");

        AccountDto newAccountDto = new AccountDto();
        newAccountDto.setFirstname("Ukkie");
        newAccountDto.setLastname("Puk");
        newAccountDto.setPhonenumber("0612345678");
        newAccountDto.setEmailaddress("ukkie@puk.com");

        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(newAccount);

        // act
        AccountDto savedAccountDto = accountService.createAccount(new AccountDto());

        // assert
        assertEquals("Piet", savedAccountDto.getFirstname());
        assertEquals("Pietje", savedAccountDto.getLastname());
        assertEquals("0611122333", savedAccountDto.getPhonenumber());
        assertEquals("piet@pietje.com", savedAccountDto.getEmailaddress());
    }

    @Test
    void deleteAccount() {
        // Arrange
        Long accountId = 1L;

        Mockito.doNothing().when(accountRepository).deleteById(accountId);

        // Act
        String result = accountService.deleteAccount(accountId);

        // Assert
        // Verify that the deleteById method of viewingRepository is called with the correct ID
        Mockito.verify(accountRepository).deleteById(accountId);

        // Verify the returned message
        assertEquals("Account successfully deleted", result);
    }
}