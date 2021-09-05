package ru.yushkov.kicksharing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                                        "name": "W001"
                                    },
                                    {
                                        "name": "W002"
                                    }
                                ]
                                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.[0].name").value("W001"))
                .andExpect(jsonPath("$.[0].kickScooterId").value(1))
                .andExpect(jsonPath("$.[1].name").value("W002"))
                .andExpect(jsonPath("$.[1].kickScooterId").value(2));
    }
}