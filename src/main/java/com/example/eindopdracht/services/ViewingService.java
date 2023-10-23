package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Property;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.ViewingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class ViewingService {

    private final ViewingRepository viewingRepository;

    public ViewingService(ViewingRepository viewingRepository) {
        this.viewingRepository = viewingRepository;
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

    private static void viewingToViewingDto(Viewing v, ViewingDto vDto) {
        vDto.setFullName(v.getFullName());
        vDto.setPhoneNumber(v.getPhoneNumber());
        vDto.setEmailAddress(v.getEmailAddress());
    }

    private static void viewingDtoToViewing(ViewingDto viewingDto, Viewing viewing) {
        viewing.setFullName(viewingDto.getFullName());
        viewing.setPhoneNumber(viewingDto.getPhoneNumber());
        viewing.setEmailAddress(viewingDto.getEmailAddress());
    }

    public ViewingDto createViewing(ViewingDto viewingDto) {
        Viewing viewing = new Viewing();
        viewingDtoToViewing(viewingDto, viewing);

        Viewing savedViewing = viewingRepository.save(viewing);

        ViewingDto savedViewingDto = new ViewingDto();
        viewingToViewingDto(savedViewing, savedViewingDto);

        return savedViewingDto;
    }

    public String deleteViewing(@RequestBody Long id) {
        viewingRepository.deleteById(id);
        return "Viewing successfully deleted";
    }
}
