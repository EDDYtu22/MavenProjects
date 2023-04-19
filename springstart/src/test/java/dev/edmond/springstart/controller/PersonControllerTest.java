package dev.edmond.springstart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import dev.edmond.springstart.mapper.PersonMapper;
import dev.edmond.springstart.service.PersonService;
import dev.edmond.springstart.validation.ObjectValidator;
import dev.edmond.springstart.web.PersonController;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private ObjectValidator personValidator;

    @MockBean
    private PersonService personService;

    @InjectMocks
    private PersonController controller;


    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldHavePaginationOnFetchAll(){

        
        //MvcResult result = mockMvc.perform(get("/persons").contentType(MediaType.APPLICATION_JSON)).andReturn();
    }


}
