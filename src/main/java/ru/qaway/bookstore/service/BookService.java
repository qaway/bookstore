package ru.qaway.bookstore.service;

import ru.qaway.bookstore.model.Book;
import ru.qaway.bookstore.model.request.BookRequest;
import ru.qaway.bookstore.model.response.BooksResponse;

import javax.validation.Valid;
import java.util.List;

public interface BookService {

    /**
     * Создает новую книгу
     * @param bookRequest - книга для создания
     */
    Book create(@Valid BookRequest bookRequest);

    /**
     * Возвращает список всех имеющихся книг
     * @return список книг
     */
    List<Book> readAll(String title, String author, Integer priceLess, Integer priceMore, Integer countMore);

    /**
     * Возвращает книгу по ее ID
     * @param id - ID клиента
     * @return - объект книги с заданным ID
     */
    Book read(int id);

    /**
     * Обновляет книгу с заданным ID,
     * в соответствии с переданным объектом
     * @param bookRequest - объект в соответсвии с которым нужно обновить данные
     * @param id - id книги которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    Book update(BookRequest bookRequest, int id);

    /**
     * Удаляет книгу с заданным ID
     * @param id - id книги, которого нужно удалить
     * @return - true если объект был удален, иначе false
     */
    boolean delete(int id);

    /**
     * Удаляет все книги
     */
    void delete();
}
