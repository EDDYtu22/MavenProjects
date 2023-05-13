package dev.edmond.swapi.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dev.edmond.swapi.validation.ObjectValidator;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
public class PersonEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectValidator validator;

    @Test
    void shouldAddValidPerson() throws Exception{
        mockMvc.perform(
            post("/swapi/persons")
            .header("Content-Type", "application/json")
            .content("""
                        
                        [
                            {
                                
                                "height": "172",
                                "mass": "77",
                                "hair_color": "blond",
                                "skin_color": "fair",
                                "eye_color": "blue",
                                "birth_year": "19BBY",
                                "gender": "male"
                            }
                        ]  

                    """)
        )
        //.andExpect(status().isOk())
        //.andExpect(jsonPath("[0].name").value("Luke Skywalker"))
        .andExpect(jsonPath("[0].height").value("172"))
        .andExpect(jsonPath("[0].mass").value("77"))
        .andExpect(jsonPath("[0].hair_color").value("blond"))
        .andExpect(jsonPath("[0].skin_color").value("fair"))
        .andExpect(jsonPath("[0].eye_color").value("blue"))
        .andExpect(jsonPath("[0].birth_year").value("19BBY"))
        .andExpect(jsonPath("[0].gender").value("male"));

    }

   
}
