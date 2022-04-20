package ru.qaway.bookstore.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.qaway.bookstore.model.Book;
import ru.qaway.bookstore.model.request.BookRequest;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Validated
@Service
public class BookServiceImpl implements BookService {

    private static final Map<Integer, Book> BOOK_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger BOOK_ID_HOLDER = new AtomicInteger();

    @Override
    public Book create(@Valid BookRequest bookRequest) {
        final int bookId = BOOK_ID_HOLDER.incrementAndGet();

        Book book = new Book(bookId, bookRequest);
        BOOK_REPOSITORY_MAP.put(bookId, book);

        return book;
    }

    @Override
    public List<Book> readAll(String title, String author, Integer priceLess, Integer priceMore, Integer countMore) {
        return BOOK_REPOSITORY_MAP.values()
                .stream()
                .filter(f -> title == null || f.getTitle().contains(title))
                .filter(f -> author == null || f.getAuthor().contains(author))
                .filter(f -> priceLess == null || f.getPrice() < priceLess)
                .filter(f -> priceMore == null || f.getPrice() > priceMore)
                .filter(f -> countMore == null || f.getCount() > countMore)
                .collect(Collectors.toList());
    }

    @Override
    public Book read(int id) {
        return BOOK_REPOSITORY_MAP.get(id);
    }

    @Override
    public Book update(BookRequest bookRequest, int id) {
        if (BOOK_REPOSITORY_MAP.containsKey(id)) {
            Book book = new Book(id, bookRequest);
            BOOK_REPOSITORY_MAP.put(id, book);
            return book;
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        return BOOK_REPOSITORY_MAP.remove(id) != null;
    }
}
