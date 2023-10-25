package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.ViewingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ViewingServiceTest {

    @Mock
    private ViewingRepository viewingRepository;

    @InjectMocks
    private ViewingService viewingService;

    @Test
    void getAllViewings() {
        // arrange
        Viewing viewing1 = new Viewing();
        viewing1.setFullname("Jan Jansen");
        viewing1.setPhonenumber("0611122333");
        viewing1.setEmailaddress("jan@jansen.com");

        Viewing viewing2 = new Viewing();
        viewing2.setFullname("Jannie de Boer");
        viewing2.setPhonenumber("0611122333");
        viewing2.setEmailaddress("jannie@deboer.com");

        List<Viewing> viewings = Arrays.asList(viewing1, viewing2);

        Mockito.when(viewingRepository.findAll()).thenReturn(viewings);

        // act
        List<ViewingDto> result = viewingService.getAllViewings();

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void createViewing() {
        // Arrange
        ViewingDto newViewingDto = new ViewingDto();
        newViewingDto.setFullname("Jannie de Boer");
        newViewingDto.setPhonenumber("0611122333");
        newViewingDto.setEmailaddress("jannie@deboer.com");

        Viewing newViewing = new Viewing();
        newViewing.setFullname(newViewingDto.getFullname());
        newViewing.setPhonenumber(newViewingDto.getPhonenumber());
        newViewing.setEmailaddress(newViewingDto.getEmailaddress());

        Mockito.when(viewingRepository.save(Mockito.any(Viewing.class))).thenReturn(newViewing);

        // Act
        ViewingDto resultDto = viewingService.createViewing(newViewingDto);

        // Assert
        // Verify that the save method of viewingRepository is called with the correct Viewing object
        Mockito.verify(viewingRepository).save(newViewing);

        // Verify that the returned newViewingDto matches the resultDto
        assertEquals(newViewingDto.getFullname(), resultDto.getFullname());
        assertEquals(newViewingDto.getPhonenumber(), resultDto.getPhonenumber());
        assertEquals(newViewingDto.getEmailaddress(), resultDto.getEmailaddress());

//        // arrange
//        Viewing newViewing = new Viewing();
//        newViewing.setFullname("Jan Jansen");
//        newViewing.setPhonenumber("0611122333");
//        newViewing.setEmailaddress("jan@jansen.com");
//
//        Mockito.when(viewingRepository.save(Mockito.any(Viewing.class))).thenReturn(newViewing);
//
//        // act
//        ViewingDto savedViewing = viewingService.createViewing(newViewing);
////        ViewingDto nvDto = viewingService.createViewing(newViewing);
//
//        // assert
//        assertEquals("Jan Jansen", savedViewing.getFullname());
//        assertEquals("0611122333", savedViewing.getPhonenumber());
//        assertEquals("jan@jansen.com", savedViewing.getEmailaddress());
//
////        assertEquals("Jan Jansen", nvDto.getFullname());
////        assertEquals("0611122333", nvDto.getPhonenumber());
////        assertEquals("jan@jansen.com", nvDto.getEmailaddress());

    }

    @Test
    void deleteViewing() {
    }
}