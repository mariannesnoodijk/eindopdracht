package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.User;
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

// Import necessary classes and annotations for testing with Mockito and JUnit
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    // Mocking the dependencies needed for testing
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserService userService;

    // Injecting the mocks into the service being tested
    @InjectMocks
    private AccountService accountService;

    // Test method for creating a new account
    @Test
    void testShouldCreateAccount() {
        // Arrange - setting up the test data for a new account
        Account newAccount = new Account();
        newAccount.setFirstname("Piet");
        newAccount.setLastname("Pietje");
        newAccount.setEmail("piet@pietje.com");

        AccountDto newAccountDto = new AccountDto();
        newAccountDto.setFirstname("Ukkie");
        newAccountDto.setLastname("Puk");
        newAccountDto.setEmail("ukkie@puk.com");

        User user = new User();

        user.setUsername("username");
        user.setPassword("password");
        user.setRoles(null);

        newAccount.setUser(user);

        // Mocking the behavior of the accountRepository
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(newAccount);

        // Act - calling the method to be tested
        AccountDto savedAccountDto = accountService.createAccount(new AccountDto());

        // Assert - checking the result
        assertEquals("Piet", savedAccountDto.getFirstname());
        assertEquals("Pietje", savedAccountDto.getLastname());
        assertEquals("piet@pietje.com", savedAccountDto.getEmail());
        assertEquals("username", savedAccountDto.getUsername());
    }

    // Test method for getting a single account
    @Test
    void testShouldGetAccount() {
        // Arrange - creating/adding a new account
        Long accountId = 1L;
        Account account = new Account();

        account.setFirstname("Piet");
        account.setLastname("Pietje");
        account.setEmail("piet@pietje.com");

        User user = new User();

        user.setUsername("username");
        user.setPassword("password");
        user.setRoles(null);

        account.setUser(user);

        // Mocking the behavior of the accountRepository
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act - calling the method to be tested
        AccountDto accountDto = accountService.getAccount(accountId);

        // Assert - checking the result
        assertEquals("Piet", accountDto.getFirstname());
        assertEquals("Pietje", accountDto.getLastname());
        assertEquals("piet@pietje.com", accountDto.getEmail());
    }

    // Test method for getting an account with an invalid ID
    @Test
    void testShouldGetAccountWithInvalidId() {
        // Arrange - setting up the test data with an invalid ID
        Long accountId = 1L;
        // Mocking the behavior of the accountRepository
        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act and Assert - checking for an exception
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            accountService.getAccount(accountId);
        });
        // Assert - checking the exception message
        assertEquals("Account not found with ID: " + accountId, exception.getMessage());
    }

    // Test method for getting all accounts
    @Test
    void testShouldGetAllAccounts() {
        // Arrange - creating/adding a new account
        Account account1 = new Account();
        account1.setFirstname("Jan");
        account1.setLastname("Jansen");
        account1.setEmail("jan@jansen.com");

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRoles(null);

        Account account2 = new Account();
        account2.setFirstname("Piet");
        account2.setLastname("Pietje");
        account2.setEmail("piet@pietje.com");

        account1.setUser(user);
        account2.setUser(user);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        // Mocking the behavior of the accountRepository
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);

        // Act - calling the method to be tested
        List<AccountDto> result = accountService.getAllAccounts();

        // Assert - checking the result
        assertEquals(2, result.size());
    }

    // Test method for deleting an account
    @Test
    void testShouldDeleteAccount() {
        // Arrange - setting up the test data for an account to be deleted
        Long accountId = 1L;
        // Mocking the behavior of the accountRepository
        Mockito.doNothing().when(accountRepository).deleteById(accountId);

        // Act - calling the method to be tested
        String result = accountService.deleteAccount(accountId);

        // Assert - verifying the interactions and checking the result
        // Verify that the deleteById method of viewingRepository is called with the correct ID
        Mockito.verify(accountRepository).deleteById(accountId);
        // Verify the returned message
        assertEquals("Account successfully deleted", result);
    }
}