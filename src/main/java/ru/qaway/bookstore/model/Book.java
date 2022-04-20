package ru.qaway.bookstore.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.qaway.bookstore.model.enums.Category;
import ru.qaway.bookstore.model.request.BookRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class Book {

    @Schema(description = "Unique id")
    private Integer id;

    @Schema(description = "Book title", example = "The Adventures of Tom Sawyer",
            required = true, minLength = 3, maxLength = 256)
    @NotEmpty
    @Size(min = 3, max = 256)
    private String title;

    @Schema(description = "Short description", example = "The story about Tom Sawyer.",
            required = true, minLength = 3, maxLength = 512)
    @NotEmpty()
    @Size(min = 3, max = 512)
    private String description;

    @Size(min = 3, max = 100)
    @Schema(description = "Author of the book", example = "Mark Twain",
            required = true, minLength = 3, maxLength = 100)
    private String author;

    @Schema(description = "Book price", example = "250",
            required = true, minimum = "0")
    @Min(0)
    private Integer price;

    @Schema(description = "Number of books", example = "10",
            required = true, minimum = "0")
    @Min(0)
    private Integer count;

    @Schema(description = "Book genre", example = "Adventures",
            required = true, oneOf = Category.class)
    private Category category;

    @Schema(description = "Last modification time")
    private OffsetDateTime lastUpdated;

    public Book(Integer id, BookRequest bookRequest) {
        this(id, bookRequest.getTitle(), bookRequest.getDescription(), bookRequest.getAuthor(),
                bookRequest.getPrice(), bookRequest.getCount(),
                bookRequest.getCategory(), OffsetDateTime.now());
    }
}
