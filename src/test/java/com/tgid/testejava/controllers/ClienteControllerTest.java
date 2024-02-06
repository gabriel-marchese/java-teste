package com.tgid.testejava.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Test
  void getAllClientes() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}