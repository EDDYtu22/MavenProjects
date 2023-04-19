package dev.edmond.springstart.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
public class PersonEndPointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddValidPerson() throws Exception {
        mockMvc.perform(
                post("/persons")
                        .header("Content-Type", "application/json")
                        .content(
                                """
                                {
                                    "name": "Ivan",
                                    "age": 14,
                                    "egnNumber": "0123456789"
                                }
                                """)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.age").value("14"))
                .andExpect(jsonPath("$.egnNumber").value("0123456789"));
    }

    @Test
    void shouldThrowValidationErrorForInvalidPerson() throws Exception {
        mockMvc.perform(
                post("/persons")
                        .header("Content-Type", "application/json")
                        .content(
                                """
                                {
                                    "name": "Ivan",
                                    "age": 14,
                                    "egnNumber": "456789"
                                }
                                """)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Person Create"))
                .andExpect(jsonPath("$.errors").isMap())
                .andExpect(jsonPath("$.clazz", CoreMatchers.nullValue()))
                .andExpect(jsonPath("$.id", CoreMatchers.instanceOf(String.class)));
    }
}
