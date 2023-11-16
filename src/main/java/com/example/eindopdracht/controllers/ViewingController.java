package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.services.ViewingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise
@RequestMapping("/viewings")
public class ViewingController {

    private final ViewingService viewingService;
// Constructor to inject the ViewingService dependency
    public ViewingController(ViewingService viewingService) {
        this.viewingService = viewingService;
    }

    // Endpoint to create a new viewing
    @PostMapping
    public ResponseEntity<ViewingDto> createViewing(@Valid @RequestBody ViewingDto viewingDto) {
        // Call the service to create a new viewing
        ViewingDto newViewing = viewingService.createViewing(viewingDto, viewingDto.getAccountId());
        // Return the new viewing and HTTP status code 201 (Created)
        return new ResponseEntity<>(newViewing, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all viewings
    @GetMapping
    public ResponseEntity<List<ViewingDto>> getAllViewings() {
        // Call the service to retrieve all viewings
        List<ViewingDto> vDto = viewingService.getAllViewings();
        // Return the list of viewings and HTTP status code 200 (OK)
        return new ResponseEntity<>(vDto, HttpStatus.OK);
    }

    // Endpoint to delete a viewing by ID
    @DeleteMapping("/{viewingId}")
    public ResponseEntity<ViewingDto> deleteViewing(@PathVariable Long viewingId) {
        // Call the service to delete a viewing by ID
        viewingService.deleteViewing(viewingId);
        // Return HTTP status code 204 (No Content) indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
