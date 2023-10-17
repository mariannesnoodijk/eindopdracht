package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.ViewingRepository;
import org.springframework.stereotype.Service;


@Service
public class ViewingService {

    private final ViewingRepository viewingRepository;

    public ViewingService(ViewingRepository viewingRepository) {
        this.viewingRepository = viewingRepository;
    }

    private static void viewingToViewingDto(Viewing v, ViewingDto vDto) {
        vDto.setId(v.getId());
        vDto.setFirstName(v.getFirstName());
        vDto.setLastName(v.getLastName());
        vDto.setEmail(v.getEmail());
        vDto.setPhoneNumber(v.getPhoneNumber());
    }

    private static void viewingDtoToViewing(ViewingDto viewingDto, Viewing viewing) {
        viewing.setId(viewingDto.getId());
        viewing.setFirstName(viewingDto.getFirstName());
        viewing.setLastName(viewingDto.getLastName());
        viewing.setEmail(viewingDto.getEmail());
        viewing.setPhoneNumber(viewingDto.getPhoneNumber());
    }

    public ViewingDto createViewing(ViewingDto viewingDto) {
        Viewing viewing = new Viewing();
        viewingDtoToViewing(viewingDto, viewing);

        Viewing savedViewing = viewingRepository.save(viewing);

        ViewingDto savedViewingDto = new ViewingDto();
        viewingToViewingDto(savedViewing, savedViewingDto);

        return savedViewingDto;
    }

}
