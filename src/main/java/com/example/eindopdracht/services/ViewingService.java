package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.ViewingDto;
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
        vDto.setFullname(v.getFullname());
        vDto.setPhonenumber(v.getPhonenumber());
        vDto.setEmailaddress(v.getEmailaddress());
        vDto.setViewingdate(v.getViewingdate());
        vDto.setViewingtime(v.getViewingtime());
    }

    private static void viewingDtoToViewing(ViewingDto viewingDto, Viewing viewing) {
        viewing.setFullname(viewingDto.getFullname());
        viewing.setPhonenumber(viewingDto.getPhonenumber());
        viewing.setEmailaddress(viewingDto.getEmailaddress());
        viewing.setViewingdate(viewingDto.getViewingdate());
        viewing.setViewingtime(viewingDto.getViewingtime());
    }

    public ViewingDto createViewing(ViewingDto viewingDto) {
        Viewing viewing = new Viewing();
        viewingDtoToViewing(viewingDto, viewing);

        Viewing savedViewing = viewingRepository.save(viewing);

        ViewingDto savedViewingDto = new ViewingDto();
        viewingToViewingDto(savedViewing, savedViewingDto);

        return savedViewingDto;
    }

    public String deleteViewing(@RequestBody Long viewingId) {
        viewingRepository.deleteById(viewingId);
        return "Viewing successfully deleted";
    }
}
