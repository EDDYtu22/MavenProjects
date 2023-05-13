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
public class StarshipEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidStarship() throws Exception{
        mockMvc.perform(
            post("/swapi/starships")
            .header("Content-Type", "application/json")
            .content("""
                        
            [
                {
                    "name": "CR90 corvette",
                    "model": "CR90 corvette",
                    "manufacturer": "Corellian Engineering Corporation",
                    "cost_in_credits": "3500000",
                    "length": "150",
                    "max_atmosphering_speed": "950",
                    "crew": "30-165",
                    "passengers": "600",
                    "cargo_capacity": "3000000",
                    "consumables": "1 year",
                    "hyperdrive_rating": "2.0",
                    "MGLT": "60",
                    "starship_class": "corvette"
                
                }
            ]

                    """)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("[0].name").value("CR90 corvette"))
        .andExpect(jsonPath("[0].model").value("CR90 corvette"))
        .andExpect(jsonPath("[0].manufacturer").value("Corellian Engineering Corporation"))
        .andExpect(jsonPath("[0].cost_in_credits").value("3500000"))
        .andExpect(jsonPath("[0].length").value("150"))
        .andExpect(jsonPath("[0].max_atmosphering_speed").value("950"))
        .andExpect(jsonPath("[0].crew").value("30-165"))
        .andExpect(jsonPath("[0].passengers").value("600"))
        .andExpect(jsonPath("[0].cargo_capacity").value("3000000"))
        .andExpect(jsonPath("[0].consumables").value("1 year"))
        .andExpect(jsonPath("[0].hyperdrive_rating").value("2.0"))
        .andExpect(jsonPath("[0].mglt").value("60"))
        .andExpect(jsonPath("[0].starship_class").value("corvette"));


    }
}
