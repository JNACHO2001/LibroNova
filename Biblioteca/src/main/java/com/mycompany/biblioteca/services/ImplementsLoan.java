
package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.noExistentResourceException;
import com.mycompany.biblioteca.model.Book;
import com.mycompany.biblioteca.model.Loan;
import com.mycompany.biblioteca.model.Partner;
import com.mycompany.biblioteca.repository.book.IBook;
import com.mycompany.biblioteca.repository.loan.ILoan;
import com.mycompany.biblioteca.repository.partner.IPartner;
import java.time.LocalDate;
import java.util.List;


public class ImplementsLoan implements IServiceLoan{
    
     private final ILoan loanRepository;
    private final IBook bookRepository;
    private final IPartner partnerRepository;

    public ImplementsLoan(ILoan loanRepository, IBook bookRepository, IPartner partnerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.partnerRepository = partnerRepository;
    }



    @Override
    public Loan createLoan(Loan loan) {
        // Validar que el socio exista
        Partner partner = partnerRepository.searchById(loan.getPartner().getId());
        if (partner == null) {
            throw new noExistentResourceException("El recurso no existe");
        }

        // Validar que el libro exista
        Book book = bookRepository.searchById(loan.getBook().getId());
        if (book == null) {
            throw new noExistentResourceException("El recurso no existe");
        }

        // Validar stock
        if (book.getStock() <= 0) {
            throw new ErrorSystemException("El libro esta agotado.");
        }

        // Crear préstamo
        Loan createdLoan = loanRepository.create(loan);

        // Reducir stock del libro
        book.setStock(book.getStock() - 1);
        bookRepository.update(book);

        return createdLoan;
    }

    @Override
    public Loan searchLoanById(int id) {
        return loanRepository.searchById(id);
    }

    @Override
    public List<Loan> listLoan() {
        
        List<Loan> loans = loanRepository.searchAll();
        if (loans.isEmpty()) {
            throw new noExistentResourceException("No hay prestamos que mostrar");
            
        }
        return loans;
        
        
       
    }

    @Override
    public void deleteLoan(int id) {
        loanRepository.delete(id);
    }

  @Override
public Loan returnLoan(int loanId) {
    // Buscar el préstamo
    Loan loan = loanRepository.searchById(loanId);
    if (loan == null) {
        throw new noExistentResourceException("El prestamo no existe");
    }
    
    // Verificar si ya fue devuelto
    if (loan.isReturned()) {
        throw new ErrorSystemException("El libro ya ha sido devuelto");
    }
    
    // Marcar como devuelto
    loan.setReturned(true);
    loan.setReturnDate(LocalDate.now());
    
    // Actualizar el préstamo en la base de datos
    Loan updatedLoan = loanRepository.update(loan);
    
    // Aumentar el stock del libro usando el ID
    Book book = bookRepository.searchById(loan.getIdBook());  // ✅ Usa getIdBook() en vez de getBook().getId()
    if (book != null) {
        book.setStock(book.getStock() + 1);
        bookRepository.update(book);
    } else {
        throw new noExistentResourceException("El libro asociado no existe");
    }
    
    return updatedLoan;
}

    
}
