package com.example.demo.dto;


import java.util.Objects;

public class BookDtos {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearOfPublication;

    public BookDtos() {
    }

    public BookDtos(Integer id, String isbn, String title, String author, String publisher, Integer yearOfPublication) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
    }

    public static BookDtos convertEntityToDto(BookDtos book) {
        return new BookDtos(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearOfPublication()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDtos bookdto = (BookDtos) o;
        return Objects.equals(id, bookdto.id) && Objects.equals(isbn, bookdto.isbn) && Objects.equals(title, bookdto.title) && Objects.equals(author, bookdto.author) && Objects.equals(publisher, bookdto.publisher) && Objects.equals(yearOfPublication, bookdto.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, publisher, yearOfPublication);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }

}
