package com.dicka.junittestexample;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloExampleResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HelloExampleResource helloExampleResource;

    @Before
    public void setUp()throws Exception{
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloExampleResource)
                .build();
    }

    @Test
    public void testHelloWorld() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world !"));
    }

    @Test
    public void testExampleHelloWorld()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test"))
                //isOk() --> pilih demand
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world !"));
    }

    @Test
    public void testCheckSpellString() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test/check"))
                .andExpect(status().isOk())
                .andExpect(content().string("test string"));
    }

    @Test
    public void testCheckJson() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test/json").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("denada rosa florina")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("mantan terindah")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)));

    }
}