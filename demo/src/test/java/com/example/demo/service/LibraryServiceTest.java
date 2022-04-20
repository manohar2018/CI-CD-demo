package com.example.demo.service;


import com.example.demo.dto.BookDtos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

class LibraryServiceTest {
    @MockBean
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService();
    }


    @Test
    public void getAllBooksAddedToListWhenStatusIsAll() {
        libraryService.getBooks();

        BookDtos bookDtos = new BookDtos(1,"12345","test title","test author","test Publisher",1992);
        BookDtos bookDtos1 = new BookDtos(2,"123456","test title","test author","test Publisher",1992);
        List<BookDtos> expectedBookList = new ArrayList<>();
        expectedBookList.add(bookDtos);
        expectedBookList.add(bookDtos1);


        BookDtos bookDtos2 = new BookDtos(1,"12345","test title","test author","test Publisher",1992);
        BookDtos bookDtos3 = new BookDtos(2,"123456","test title","test author","test Publisher",1992);
        List<BookDtos> actualBookList = new ArrayList<>();
        actualBookList.add(bookDtos2);
        actualBookList.add(bookDtos3);

        Assertions.assertEquals(expectedBookList,actualBookList);
    }
}