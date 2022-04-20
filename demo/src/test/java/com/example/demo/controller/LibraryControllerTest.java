package com.example.demo.controller;

import com.example.demo.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    void shouldExpectOkForBooksList() throws Exception {
        mockMvc.perform(get("/api/v1/library/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(libraryService, times(1)).getBooks();

    }


    @Test
    void shouldExpect4xxIfURLIsWrong() throws Exception {
        mockMvc.perform(get("/api/v1/library")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(libraryService, times(0)).getBooks();

    }



}