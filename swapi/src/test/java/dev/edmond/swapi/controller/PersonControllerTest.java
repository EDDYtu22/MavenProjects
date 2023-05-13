package dev.edmond.swapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dev.edmond.swapi.mapper.PersonMapper;
import dev.edmond.swapi.models.Person;
import dev.edmond.swapi.service.PersonService;
import dev.edmond.swapi.validation.ObjectValidator;
import dev.edmond.swapi.web.PersonController;


@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
    
    @MockBean
    private PersonService personService;

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private ObjectValidator validator;

    @InjectMocks
    private PersonController controller;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldHavePaginationOnFetchAll() throws Exception {
        Page<Person> emptyPersonPage = new PageImpl<>(
            Collections.emptyList(), 
            PageRequest.of(0, 10) , 0);
        when(personService.fetchAll(0, 10)).thenReturn(emptyPersonPage);
       
        mockMvc.perform(
            get("/swapi/persons")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(0))
            .andExpect(jsonPath("$.nextPage").value(IsNull.nullValue()))
            .andExpect(jsonPath("$.prevPage").value(IsNull.nullValue()));
    }

    @Test
    void shouldHaveUrlForNextPageOnFetchAll() throws Exception {
        Page<Person> emptyPersonPage = new PageImpl<>(
            Collections.emptyList(), 
            PageRequest.of(0, 10) , 20);
        when(personService.fetchAll(0, 10)).thenReturn(emptyPersonPage);
       
       
        mockMvc.perform(
            get("/swapi/persons")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(20))
            .andExpect(jsonPath("$.nextPage").value("http://localhost:8080/swapi/persons?currPage=2"))
            .andExpect(jsonPath("$.prevPage").value(IsNull.nullValue()));
    }
    @Test
    void shouldHaveUrlForPrevPageOnFetchAll() throws Exception {
        Page<Person> emptyPersonPage = new PageImpl<>(
            Collections.emptyList(), 
            PageRequest.of(1, 10) , 20);
        when(personService.fetchAll(1, 10)).thenReturn(emptyPersonPage);
       
        mockMvc.perform(
            get("/swapi/persons")
            .param("currPage", "2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(20))
            .andExpect(jsonPath("$.nextPage").value(IsNull.nullValue()))
            .andExpect(jsonPath("$.prevPage").value("http://localhost:8080/swapi/persons?currPage=1"));
    }

    @Test
    void shouldHaveUrlForPrevAndNextPageOnFetchAll() throws Exception {
        Page<Person> emptyPersonPage = new PageImpl<>(
            Collections.emptyList(), 
            PageRequest.of(1, 10) , 30);
        when(personService.fetchAll(1, 10)).thenReturn(emptyPersonPage);
       
        mockMvc.perform(
            get("/swapi/persons")
            .param("currPage", "2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.count").value(30))
            .andExpect(jsonPath("$.nextPage").value("http://localhost:8080/swapi/persons?currPage=3"))
            .andExpect(jsonPath("$.prevPage").value("http://localhost:8080/swapi/persons?currPage=1"));
    }


    @Test
    void shouldReturnBadRequestWithErrors() throws Exception {

        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("name", "cannot be null");

        when(
            validator.validate(any())
        ).thenReturn(validationErrors);
        
        mockMvc.perform(post("/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                "height": "167",
                "egnNumber": "88888888888"
            }
            """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.age").value("cannot be negative"));
    }

}
