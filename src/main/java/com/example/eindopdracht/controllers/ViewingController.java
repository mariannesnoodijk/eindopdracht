package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.services.ViewingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viewings")
public class ViewingController {

    private final ViewingService viewingService;

    public ViewingController(ViewingService viewingService) {
        this.viewingService = viewingService;
    }

    @PostMapping
    public ResponseEntity<ViewingDto> createViewing(@RequestBody ViewingDto viewingDto) {
        ViewingDto newViewing = viewingService.createViewing(viewingDto);
        return new ResponseEntity<>(newViewing, HttpStatus.CREATED);
    }
}
