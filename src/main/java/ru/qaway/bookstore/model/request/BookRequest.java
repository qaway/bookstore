package ru.qaway.bookstore.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.qaway.bookstore.model.enums.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookRequest {

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

    @Schema(description = "Author of the book", example = "Mark Twain",
            required = true, minLength = 3, maxLength = 100)
    @NotEmpty
    @Size(min = 3, max = 100)
    private String author;

    @Schema(description = "Book price", example = "250",
            required = true, minimum = "0")
    @NotNull
    @Min(0)
    private Integer price;

    @Schema(description = "Number of books", example = "10",
            required = true, minimum = "0")
    @NotNull
    @Min(0)
    private Integer count;

    @Schema(description = "Book genre", example = "Adventures",
            required = true, oneOf = Category.class)
    @NotNull
    private Category category;
}
