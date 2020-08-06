package com.samlinasoft.gcp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleApplicationController.class)
@ContextConfiguration
@TestPropertySource(properties = { "VERSION:1.20", "COMMIT_SHA:4242A" })
public class SimpleApplicationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenVersionEndpointCalled_then_returnsCorrectVerisonInformation() throws Exception{

        mvc.perform(get("/version")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath(".myApplication[0].version").value("1.20"))
            .andExpect(jsonPath(".myApplication[0].lastcommitsha").value("4242A"))
            .andExpect(jsonPath(".myApplication[0].description").value("pre-interview technical test"));
    }
}
