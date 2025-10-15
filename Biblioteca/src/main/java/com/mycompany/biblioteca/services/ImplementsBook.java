package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.exeptions.DuplicateExceptionRecord;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.ResourceNotFound;
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
       
        
            if (book.getStock() < 0) {
                 throw new IllegalArgumentException("El stock no puede ser negativo");
                
            }
            
            return bookRepository.create(book);
       
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
        List<Book> books =bookRepository.searchAll();
        if (books.isEmpty()) {
            throw new ResourceNotFound("No existen libros");
            
        }
        return  books;
       
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
