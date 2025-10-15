package com.mycompany.biblioteca;

//import com.mycompany.biblioteca.db.Conexion;

import com.mycompany.biblioteca.repository.book.IBook;
import com.mycompany.biblioteca.repository.loan.ILoan;
import com.mycompany.biblioteca.repository.partner.IPartner;
import com.mycompany.biblioteca.services.ImplementsBook;
import com.mycompany.biblioteca.services.ImplementsLoan;
import com.mycompany.biblioteca.services.ImplementsPartner;
import com.mycompany.biblioteca.view.BibliotecaView;

//import com.mycompany.biblioteca.model.Book;
//import com.mycompany.biblioteca.model.Partner;
//import com.mycompany.biblioteca.repository.book.IBook;
//import com.mycompany.biblioteca.repository.partner.IPartner;

//import java.sql.Connection;
public class Biblioteca {

    public static void main(String[] args) {
        
       // Crear repositorios
        IBook bookRepo = new IBook();
        IPartner partnerRepo = new IPartner();
        ILoan loanRepo = new ILoan();

        // Crear servicios
        ImplementsBook bookService = new ImplementsBook(bookRepo);
        ImplementsPartner partnerService = new ImplementsPartner(partnerRepo);
        ImplementsLoan loanService = new ImplementsLoan(loanRepo, bookRepo, partnerRepo);

        // Crear vista
        BibliotecaView application = new BibliotecaView(bookService, partnerService, loanService);

        // Mostrar menú principal
        application.mostrarMenuPrincipal();

     
        
       
    }





}

  /*
        try(Connection conn =Conexion.getConnection()) {
            
            
        
            
            if (conn !=null) {
                System.out.print("esta conectada ");
                
            }else{
                   System.out.print(" no esta conectada ");
            
            }
          
            
        } catch (Exception e) {
            System.out.println("error" + e.getMessage() );
        }
      
    }
*/
          


