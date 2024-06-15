package ru.qaway.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qaway.bookstore.model.Book;
import ru.qaway.bookstore.model.request.BookRequest;
import ru.qaway.bookstore.model.response.BooksResponse;
import ru.qaway.bookstore.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Books", value = "APIs for books management")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Add new book to catalog", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping(value = "/books")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest bookRequest) {
        Book book = bookService.create(bookRequest);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @Operation(summary = "Get books from catalog", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(value = "/books")
    public ResponseEntity<BooksResponse> read(
            @Parameter(description = "Position to start from. Default value : 0.")
            @RequestParam(value = "from", required = false) Integer from,
            @Parameter(description = "The number of items per page.  Default value : 10.")
            @RequestParam(value = "perPage", required = false) Integer perPage,
            @Parameter(description = "Filters books list based on book's title.")
            @RequestParam(value = "title", required = false) String title,
            @Parameter(description = "Filters books list based on author's name.")
            @RequestParam(value = "author", required = false) String author,
            @Parameter(description = "Filters books list based on price. Returns books with price less than value")
            @RequestParam(value = "priceLess", required = false) Integer priceLess,
            @Parameter(description = "Filters books list based on price. Returns books with price more than value")
            @RequestParam(value = "priceMore", required = false) Integer priceMore,
            @Parameter(description = "Filters books list based on count. Returns books with count more than value")
            @RequestParam(value = "countMore", required = false) Integer countMore) {
        final List<Book> books = bookService.readAll(title, author, priceLess, priceMore, countMore);

        from = from != null ? from : 0;
        perPage = perPage != null ? perPage : 10;

        BooksResponse response = new BooksResponse(books.subList(from,
                Math.min(from + perPage, books.size())), books.size());

        return books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get book from catalog by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> read(
            @Parameter(description = "Identifier of the book.")
            @PathVariable(name = "id") int id) {
        final Book book = bookService.read(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update existed book by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Book> update(
            @Parameter(description = "Identifier of the book.")
            @PathVariable(name = "id") int id,
            @Valid @RequestBody BookRequest bookRequest) {
        Book book = bookService.update(bookRequest, id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete book by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Identifier of the book.")
            @PathVariable(name = "id") int id) {
        final boolean deleted = bookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete all books")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    @DeleteMapping(value = "/books")
    public ResponseEntity<?> delete() {
        bookService.delete();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
