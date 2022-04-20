package com.example.demo.service;
;
import com.example.demo.dto.BookDtos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LibraryService {
    public List<BookDtos> getBooks() {
        BookDtos bookDtos = new BookDtos(1,"12345","test title","test author","test Publisher",1992);
        BookDtos bookDtos1 = new BookDtos(1,"123456","test title","test author","test Publisher",1992);
        List<BookDtos> bookList = new ArrayList<>();
        bookList.add(bookDtos);
        bookList.add(bookDtos1);
     return bookList;
    }


}