package ru.yushkov.kicksharing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class KickScooterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateKickScooter() throws Exception {
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

    @Test
    void shouldGetKickScooter() throws Exception {
        this.mvc.perform(get("/kickscooters/all")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("W001"))
                .andExpect(jsonPath("$.[0].status").value("AVAILABLE"))
                .andExpect(jsonPath("$.[0].kickScooterId").value(1))
                .andExpect(jsonPath("$.[1]").exists())
                .andExpect(jsonPath("$.[2]").exists())
                .andExpect(jsonPath("$.[3]").exists())
                .andExpect(jsonPath("$.[4]").exists())
                .andExpect(jsonPath("$.[5]").exists())
                .andExpect(jsonPath("$.[6]").exists())
                .andExpect(jsonPath("$.[7]").exists())
                .andExpect(jsonPath("$.[8]").exists())
                .andExpect(jsonPath("$.[9]").exists());
    }

    @Test
    void shouldDeleteKickScooter() throws Exception {
        this.mvc.perform(delete("/kickscooters/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("KickScooter deleted"));
    }
}