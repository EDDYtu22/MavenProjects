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
public class SpecieEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidSpecie() throws Exception{
        mockMvc.perform(
            post("/swapi/species")
            .header("Content-Type", "application/json")
            .content("""
                        
            [
                {
                    "name": "Human",
                    "classification": "mammal",
                    "designation": "sentient",
                    "average_height": "180",
                    "skin_colors": "caucasian, black, asian, hispanic",
                    "hair_colors": "blonde, brown, black, red",
                    "eye_colors": "brown, blue, green, hazel, grey, amber",
                    "average_lifespan": "120",
                    "language": "Galactic Basic"
                    
                }
            ]

                    """)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("[0].name").value("Human"))
        .andExpect(jsonPath("[0].classification").value("mammal"))
        .andExpect(jsonPath("[0].designation").value("sentient"))
        .andExpect(jsonPath("[0].average_height").value("180"))
        .andExpect(jsonPath("[0].skin_colors").value("caucasian, black, asian, hispanic"))
        .andExpect(jsonPath("[0].hair_colors").value("blonde, brown, black, red"))
        .andExpect(jsonPath("[0].eye_colors").value("brown, blue, green, hazel, grey, amber"))
        .andExpect(jsonPath("[0].average_lifespan").value("120"))
        .andExpect(jsonPath("[0].language").value("Galactic Basic"));
       
    }
}
