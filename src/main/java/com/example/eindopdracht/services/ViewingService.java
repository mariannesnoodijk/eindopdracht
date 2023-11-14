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

    public ViewingService(ViewingRepository viewingRepository, AccountRepository accountRepository) {
        this.viewingRepository = viewingRepository;
        this.accountRepository = accountRepository;
    }

    public ViewingDto createViewing(ViewingDto viewingDto, Long accountId) {
        Viewing viewing = new Viewing();
        viewingDtoToViewing(viewingDto, viewing);

        // Retrieve the associated account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IdNotFoundException("Account not found with id: " + accountId));

        viewing.setAccount(account); // Associates the viewing with the account
        viewingDto.setAccountId(account.getAccountId()); // Adds the accountID to the viewingDTO

        Viewing savedViewing = viewingRepository.save(viewing);

        ViewingDto savedViewingDto = new ViewingDto();
        viewingToViewingDto(savedViewing, savedViewingDto);
        savedViewingDto.setAccountId(account.getAccountId());

        return savedViewingDto;
    }

    public List<ViewingDto> getAllViewings() {

        List<Viewing> viewings = viewingRepository.findAll();
        List<ViewingDto> viewingDtos = new ArrayList<>();

        for (Viewing v : viewings) {
            ViewingDto vDto = new ViewingDto();
            viewingToViewingDto(v, vDto);

            viewingDtos.add(vDto);
        }
        return viewingDtos;
    }

    private static void viewingToViewingDto(Viewing viewing, ViewingDto vDto) {
        vDto.setFullname(viewing.getFullname());
        vDto.setPhonenumber(viewing.getPhonenumber());
        vDto.setEmailaddress(viewing.getEmailaddress());
        vDto.setDate(viewing.getDate());
        vDto.setTime(viewing.getTime());
        vDto.setAccountId(viewing.getAccount().getAccountId());
        vDto.setViewingId(viewing.getViewingId());
    }

    private static void viewingDtoToViewing(ViewingDto viewingDto, Viewing viewing) {
        viewing.setFullname(viewingDto.getFullname());
        viewing.setPhonenumber(viewingDto.getPhonenumber());
        viewing.setEmailaddress(viewingDto.getEmailaddress());
        viewing.setDate(viewingDto.getDate());
        viewing.setTime(viewingDto.getTime());
        viewing.setViewingId(viewingDto.getViewingId());
    }


    public String deleteViewing(@RequestBody Long viewingId) {
        viewingRepository.deleteById(viewingId);
        return "Viewing successfully deleted";
    }
}
