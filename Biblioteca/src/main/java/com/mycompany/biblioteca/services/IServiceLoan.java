
package com.mycompany.biblioteca.services;

import com.mycompany.biblioteca.model.Loan;
import java.util.List;

public interface IServiceLoan {
     // Crear un producto
    Loan createLoan(Loan l);

    // Buscar producto por ID
    Loan searchLoanById(int id);

    // Listar todos los productos
    List<Loan> listLoan();

    // Eliminar un producto por ID
    void deleteLoan(int id);
    
     Loan returnLoan(int loanId);
    
    
}
