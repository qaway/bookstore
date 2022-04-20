package ru.qaway.bookstore.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.qaway.bookstore.model.Book;

import java.util.List;

@Data
@AllArgsConstructor
public class BooksResponse {

    private List<Book> books;
    private Integer size;
}
