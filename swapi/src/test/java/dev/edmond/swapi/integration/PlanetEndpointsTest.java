package dev.edmond.swapi.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
public class PlanetEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidPlanet() throws Exception{
        mockMvc.perform(
            post("/swapi/planets")
            .header("Content-Type", "application/json")
            .content("""
                        
            [
                {
                    "name": "Tatooine",
                    "rotation_period": "23",
                    "orbital_period": "304",
                    "diameter": "10465",
                    "climate": "arid",
                    "gravity": "1 standard",
                    "terrain": "desert",
                    "surface_water": "1",
                    "population": "200000"
                    
                }
            ]

                    """)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("[0].name").value("Tatooine"))
        .andExpect(jsonPath("[0].rotation_period").value("23"))
        .andExpect(jsonPath("[0].orbital_period").value("304"))
        .andExpect(jsonPath("[0].diameter").value("10465"))
        .andExpect(jsonPath("[0].climate").value("arid"))
        .andExpect(jsonPath("[0].gravity").value("1 standard"))
        .andExpect(jsonPath("[0].terrain").value("desert"))
        .andExpect(jsonPath("[0].surface_water").value("1"))
        .andExpect(jsonPath("[0].population").value("200000"));


    }
}
