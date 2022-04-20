package com.example.demo.controller;


import com.example.demo.dto.BookDtos;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

private LibraryService libraryService;

@Autowired
    public LibraryController(LibraryService libraryService){
    this.libraryService =libraryService;
}

@GetMapping("/")
    public ResponseEntity<List<BookDtos>> getBooks(){
    List<BookDtos> bookList = libraryService.getBooks();
    return ResponseEntity.ok().body(bookList);

}

}
