package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.AccountRepository;
import com.example.eindopdracht.repositories.ViewingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class ViewingService {

    private final ViewingRepository viewingRepository;
    private final AccountRepository accountRepository;

    // Constructor to inject dependencies
    public ViewingService(ViewingRepository viewingRepository, AccountRepository accountRepository) {
        this.viewingRepository = viewingRepository;
        this.accountRepository = accountRepository;
    }

    // Create a new viewing
    public ViewingDto createViewing(ViewingDto viewingDto, Long accountId) {
        Viewing viewing = new Viewing();
        viewingDtoToViewing(viewingDto, viewing);

        // Find the associated account or throw an exception if not found
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IdNotFoundException("Account not found with id: " + accountId));

        // Set the account for the viewing
        viewing.setAccount(account);
        // Set the account ID in the DTO for consistency
        viewingDto.setAccountId(account.getAccountId());

        // Save the viewing
        Viewing savedViewing = viewingRepository.save(viewing);

        // Convert the saved viewing to DTO
        ViewingDto savedViewingDto = new ViewingDto();
        viewingToViewingDto(savedViewing, savedViewingDto);
        // Set the account ID in the DTO for consistency
        savedViewingDto.setAccountId(account.getAccountId());

        return savedViewingDto;
    }

    // Retrieve all viewings
    public List<ViewingDto> getAllViewings() {

        List<Viewing> viewings = viewingRepository.findAll();
        List<ViewingDto> viewingDtos = new ArrayList<>();

        // Convert Viewing entities to ViewingDto
        for (Viewing v : viewings) {
            ViewingDto vDto = new ViewingDto();
            viewingToViewingDto(v, vDto);

            viewingDtos.add(vDto);
        }
        return viewingDtos;
    }

    // Helper method to convert Viewing to ViewingDto
    private static void viewingToViewingDto(Viewing viewing, ViewingDto vDto) {
        vDto.setFullname(viewing.getFullname());
        vDto.setPhonenumber(viewing.getPhonenumber());
        vDto.setEmail(viewing.getEmail());
        vDto.setDate(viewing.getDate());
        vDto.setTime(viewing.getTime());
        vDto.setAccountId(viewing.getAccount().getAccountId());
        vDto.setViewingId(viewing.getViewingId());
    }

    // Helper method to convert ViewingDto to Viewing
    private static void viewingDtoToViewing(ViewingDto viewingDto, Viewing viewing) {
        viewing.setFullname(viewingDto.getFullname());
        viewing.setPhonenumber(viewingDto.getPhonenumber());
        viewing.setEmail(viewingDto.getEmail());
        viewing.setDate(viewingDto.getDate());
        viewing.setTime(viewingDto.getTime());
        viewing.setViewingId(viewingDto.getViewingId());
    }

    // Delete a viewing by ID
    public String deleteViewing(@RequestBody Long viewingId) {
        viewingRepository.deleteById(viewingId);
        return "Viewing successfully deleted";
    }
}
