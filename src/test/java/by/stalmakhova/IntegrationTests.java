package by.stalmakhova;


import by.stalmakhova.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @Autowired
    private JWTUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void WhenTryToGetUnknownEndpoint_Then4xxStatus() throws Exception {
        String token = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/tracks/AddTrack").header("Authorization", "Bearer " + token))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void WhenTryToGetAllUsers_AndHasAToken_Then200Status()  throws Exception {
        var token = jwtUtil.generateToken("vanya");
        mockMvc.perform(MockMvcRequestBuilders.get("/schedule/History").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}