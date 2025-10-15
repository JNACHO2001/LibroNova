package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.model.Book;
import java.util.List;

public interface IServiceBook {
    Book createBook(Book book);

    // Buscar libro por ID
    Book searchBookById(int id);

    // Listar todos los libros
    List<Book> listBooks();

    // Actualizar un libro
    Book updateBook(Book book);

    // Eliminar un libro por ID
    void deleteBook(int id);

}
