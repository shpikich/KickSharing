package ru.yushkov.kicksharing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yushkov.kicksharing.entity.Status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.yushkov.kicksharing.entity.Status.AVAILABLE;

@SpringBootTest
@AutoConfigureMockMvc
class KickScooterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldProcessPostRequest() throws Exception {
        this.mvc.perform(post("/kickscooters")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        [
                            {
                                "name": "W050"
                            },
                            {
                                "name": "W051"
                            }
                        ]
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.[0].name").value("W050"))
                .andExpect(jsonPath("$.[0].status").value("AVAILABLE"))
                .andExpect(jsonPath("$.[0].kickScooterId").value(11))
                .andExpect(jsonPath("$.[1].name").value("W051"))
                .andExpect(jsonPath("$.[1].status").value("AVAILABLE"))
                .andExpect(jsonPath("$.[1].kickScooterId").value(12));
    }
}