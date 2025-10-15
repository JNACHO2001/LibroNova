package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.exeptions.DuplicateExceptionRecord;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.noExistentResourceException;
import com.mycompany.biblioteca.model.Book;
import com.mycompany.biblioteca.repository.book.IBook;

import java.util.List;

public class ImplementsBook implements IServiceBook {

    private final IBook bookRepository;

    public ImplementsBook(IBook bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        // Validar que el título no esté duplicado
        try {
            return bookRepository.create(book);
        } catch (DuplicateExceptionRecord e) {
            throw new DuplicateExceptionRecord("A book with this title already exists.");
        } catch (Exception e) {
            throw new ErrorSystemException("Error creating the book: " + e.getMessage());
        }
    }

    @Override
    public Book searchBookById(int id) {
        Book book = bookRepository.searchById(id);
        if (book == null) {
            throw new noExistentResourceException("The book does not exist.");
        }
        return book;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.searchAll();
    }

    @Override
    public Book updateBook(Book book) {
        // Validar existencia
        Book existingBook = bookRepository.searchById(book.getId());
        if (existingBook == null) {
            throw new noExistentResourceException("The book does not exist.");
        }

        return bookRepository.update(book);
    }

    @Override
    public void deleteBook(int id) {
        Book book = bookRepository.searchById(id);
        if (book == null) {
            throw new noExistentResourceException("The book does not exist.");
        }
        bookRepository.delete(id);
    }
}
