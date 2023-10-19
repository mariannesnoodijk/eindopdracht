package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.services.ViewingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viewings")
public class ViewingController {

    private final ViewingService viewingService;

    public ViewingController(ViewingService viewingService) {
        this.viewingService = viewingService;
    }

    @GetMapping
    public ResponseEntity<List<ViewingDto>> getAllViewings() {
        List<ViewingDto> vDto = viewingService.getAllViewings();
        return new ResponseEntity<>(vDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ViewingDto> createViewing(@RequestBody ViewingDto viewingDto) {
        ViewingDto newViewing = viewingService.createViewing(viewingDto);
        return new ResponseEntity<>(newViewing, HttpStatus.CREATED);
    }
}
