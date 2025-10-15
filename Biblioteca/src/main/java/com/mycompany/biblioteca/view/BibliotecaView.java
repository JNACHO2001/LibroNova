package com.mycompany.biblioteca.view;

import com.mycompany.biblioteca.model.Book;
import com.mycompany.biblioteca.model.Loan;
import com.mycompany.biblioteca.model.Partner;
import com.mycompany.biblioteca.services.ImplementsBook;
import com.mycompany.biblioteca.services.ImplementsLoan;
import com.mycompany.biblioteca.services.ImplementsPartner;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class BibliotecaView {
    
    private final ImplementsBook bookService;
    private final ImplementsPartner partnerService;
    private final ImplementsLoan loanService;

    public BibliotecaView(ImplementsBook bookService, ImplementsPartner partnerService, ImplementsLoan loanService) {
        this.bookService = bookService;
        this.partnerService = partnerService;
        this.loanService = loanService;
    }

    public void mostrarMenuPrincipal() {
        String menu = "1. Libros\n2. Socios\n3. Préstamos\n4. Salir";
        int opcion;

        do {
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break; // Cancelar
            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1 -> menuLibros();
                case 2 -> menuSocios();
                case 3 -> menuPrestamos();
                case 4 -> JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }

        } while (opcion != 4);
    }

    private void menuLibros() {
        String menu = "1. Crear libro\n2. Listar libros\n3. Buscar libro\n4. Eliminar libro\n5. Volver";
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

        switch (opcion) {
            case 1 -> crearLibro();
            case 2 -> listarLibros();
            case 3 -> buscarLibro();
            case 4 -> eliminarLibro();
            case 5 -> mostrarMenuPrincipal();
        }
    }

    private void crearLibro() {
        String title = JOptionPane.showInputDialog("Título:");
        String author = JOptionPane.showInputDialog("Autor:");
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setStock(stock);

        bookService.createBook(book);

        JOptionPane.showMessageDialog(null, "Libro creado correctamente.");
    }

    private void listarLibros() {
        List<Book> books = bookService.listBooks();
        StringBuilder sb = new StringBuilder();
        for (Book b : books) {
            sb.append(b.getId()).append(" - ").append(b.getTitle())
              .append(" (").append(b.getStock()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No hay libros.");
    }

    private void buscarLibro() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del libro:"));
        Book book = bookService.searchBookById(id);
        if (book != null) {
            JOptionPane.showMessageDialog(null, "Libro: " + book.getTitle() + " - Stock: " + book.getStock());
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
        }
    }

    private void eliminarLibro() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del libro a eliminar:"));
        bookService.deleteBook(id);
        JOptionPane.showMessageDialog(null, "Libro eliminado.");
    }

    // ================== Socios ==================
    private void menuSocios() {
        String menu = "1. Crear socio\n2. Listar socios\n3. Buscar socio\n4. Eliminar socio\n5. Volver";
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

        switch (opcion) {
            case 1 -> crearSocio();
            case 2 -> listarSocios();
            case 3 -> buscarSocio();
            case 4 -> eliminarSocio();
            case 5 -> mostrarMenuPrincipal();
        }
    }

    private void crearSocio() {
        String name = JOptionPane.showInputDialog("Nombre del socio:");
        Partner partner = new Partner();
        partner.setName(name);

        partnerService.createPartner(partner);
        JOptionPane.showMessageDialog(null, "Socio creado correctamente.");
    }

    private void listarSocios() {
        List<Partner> partners = partnerService.listPartners();
        StringBuilder sb = new StringBuilder();
        for (Partner p : partners) {
            sb.append(p.getId()).append(" - ").append(p.getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No hay socios.");
    }

    private void buscarSocio() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del socio:"));
        Partner p = partnerService.searchPartnerById(id);
        if (p != null) {
            JOptionPane.showMessageDialog(null, "Socio: " + p.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Socio no encontrado.");
        }
    }

    private void eliminarSocio() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del socio a eliminar:"));
        partnerService.deletePartner(id);
        JOptionPane.showMessageDialog(null, "Socio eliminado.");
    }

    // ================== Préstamos ==================
    private void menuPrestamos() {
        String menu = "1. Crear préstamo\n2. Listar préstamos\n3. Buscar préstamo\n4. Devolver libro\n5. Volver";
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

        switch (opcion) {
            case 1 -> crearPrestamo();
            case 2 -> listarPrestamos();
            case 3 -> buscarPrestamo();
            case 4 -> devolverPrestamo();
            case 5 -> mostrarMenuPrincipal();
        }
    }

    private void crearPrestamo() {
        int bookId = Integer.parseInt(JOptionPane.showInputDialog("ID del libro:"));
        int partnerId = Integer.parseInt(JOptionPane.showInputDialog("ID del socio:"));
        LocalDate deliveryDate = LocalDate.now();

        Book book = bookService.searchBookById(bookId);
        Partner partner = partnerService.searchPartnerById(partnerId);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setPartner(partner);
        loan.setDeliveryDate(deliveryDate);

        loanService.createLoan(loan);
        JOptionPane.showMessageDialog(null, "Préstamo creado.");
    }

    private void listarPrestamos() {
        List<Loan> loans = loanService.listLoan();
        StringBuilder sb = new StringBuilder();
        for (Loan l : loans) {
            sb.append(l.getId()).append(" - Libro: ").append(l.getBook().getTitle())
              .append(" - Socio: ").append(l.getPartner().getName())
              .append(" - Devuelto: ").append(l.isReturned()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No hay préstamos.");
    }

    private void buscarPrestamo() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del préstamo:"));
        Loan loan = loanService.searchLoanById(id);
        if (loan != null) {
            JOptionPane.showMessageDialog(null, "Préstamo: Libro: " + loan.getBook().getTitle() +
                    " - Socio: " + loan.getPartner().getName() + " - Devuelto: " + loan.isReturned());
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo no encontrado.");
        }
    }

    private void devolverPrestamo() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del préstamo a devolver:"));
        loanService.returnLoan(id);
        JOptionPane.showMessageDialog(null, "Préstamo marcado como devuelto.");
    }

}
