package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.services.ViewingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/viewings") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class ViewingController {

    private final ViewingService viewingService;

    public ViewingController(ViewingService viewingService) {
        this.viewingService = viewingService;
    }

    @PostMapping // This method handles HTTP POST requests to the /viewings endpoint creating a viewing
    public ResponseEntity<ViewingDto> createViewing(@Valid @RequestBody ViewingDto viewingDto) {
        ViewingDto newViewing = viewingService.createViewing(viewingDto, viewingDto.getAccountId());
        return new ResponseEntity<>(newViewing, HttpStatus.CREATED);
    }

    @GetMapping // This method handles HTTP GET requests to the /viewings endpoint
    public ResponseEntity<List<ViewingDto>> getAllViewings() {
        List<ViewingDto> vDto = viewingService.getAllViewings();
        return new ResponseEntity<>(vDto, HttpStatus.OK);
    }

    @DeleteMapping("/{viewingId}") // This method handles HTTP DELETE requests to the /viewings/{viewingId} endpoint, where {id} is a path variable representing the property ID
    public ResponseEntity<ViewingDto> deleteViewing(@PathVariable Long viewingId) {
        viewingService.deleteViewing(viewingId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
