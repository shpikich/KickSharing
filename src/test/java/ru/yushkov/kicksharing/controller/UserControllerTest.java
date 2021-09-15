package ru.yushkov.kicksharing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.repository.UserRepository;
import ru.yushkov.kicksharing.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {
        this.mvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "name": "Nikita",
                                    "surname": "Yushkov",
                                    "age": 24
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nikita"))
                .andExpect(jsonPath("$.surname").value("Yushkov"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    void shouldUpdateUserAge() throws Exception {
        User user = new User.Builder()
                .withName("Ivan")
                .withSurname("Ivanov")
                .withAge(25)
                .build();

        userService.addUser(user);

        this.mvc.perform(put("/users/{user_id}", 1)
                        .param("age", "55")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.surname").value("Ivanov"))
                .andExpect(jsonPath("$.age").value(55))
                .andExpect(jsonPath("$.userId").value(1));
    }
}