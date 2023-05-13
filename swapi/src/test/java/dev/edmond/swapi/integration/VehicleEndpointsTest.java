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
public class VehicleEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidVehicle() throws Exception{
        mockMvc.perform(
            post("/swapi/vehicles")
            .header("Content-Type", "application/json")
            .content("""
                        
            [
                {
                    "name": "Sand Crawler",
                    "model": "Digger Crawler",
                    "manufacturer": "Corellia Mining Corporation",
                    "cost_in_credits": "150000",
                    "length": "36.8",
                    "max_atmosphering_speed": "30",
                    "crew": "46",
                    "passengers": "30",
                    "cargo_capacity": "50000",
                    "consumables": "2 months",
                    "vehicle_class": "wheeled"

                }
            ]

                    """)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("[0].name").value("Sand Crawler"))
        .andExpect(jsonPath("[0].model").value("Digger Crawler"))
        .andExpect(jsonPath("[0].manufacturer").value("Corellia Mining Corporation"))
        .andExpect(jsonPath("[0].cost_in_credits").value("150000"))
        .andExpect(jsonPath("[0].length").value("36.8"))
        .andExpect(jsonPath("[0].max_atmosphering_speed").value("30"))
        .andExpect(jsonPath("[0].crew").value("46"))
        .andExpect(jsonPath("[0].passengers").value("30"))
        .andExpect(jsonPath("[0].cargo_capacity").value("50000"))
        .andExpect(jsonPath("[0].consumables").value("2 months"))
        .andExpect(jsonPath("[0].vehicle_class").value("wheeled"));


    }
}
