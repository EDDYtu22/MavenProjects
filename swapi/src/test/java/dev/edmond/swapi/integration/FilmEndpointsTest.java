package dev.edmond.swapi.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class FilmEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidFilm() throws Exception{
        mockMvc.perform(
            post("/swapi/films")
            .header("Content-Type", "application/json")
            .content("""
                {
                    "title": "A New Hope",
                    "director": "George Lucas",
                    "producer": "Gary Kurtz, Rick McCallum",
                    "release_date": "1977-05-25"
                    
                }
                    """)
        ).andExpect(status().isOk()); 
    }
}
