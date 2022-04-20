package ru.qaway.bookstore.service;

import ru.qaway.bookstore.model.Book;
import ru.qaway.bookstore.model.request.BookRequest;
import ru.qaway.bookstore.model.response.BooksResponse;

import javax.validation.Valid;
import java.util.List;

public interface BookService {

    /**
     * Создает нового клиента
     * @param bookRequest - клиент для создания
     */
    Book create(@Valid BookRequest bookRequest);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<Book> readAll(String title, String author, Integer priceLess, Integer priceMore, Integer countMore);

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    Book read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param bookRequest - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    Book update(BookRequest bookRequest, int id);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}
