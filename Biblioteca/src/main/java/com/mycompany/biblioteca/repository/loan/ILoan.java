package com.mycompany.biblioteca.repository.loan;

import com.mycompany.biblioteca.db.Conexion;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.ResourceNotFound;
import com.mycompany.biblioteca.model.Book;
import com.mycompany.biblioteca.model.Loan;
import com.mycompany.biblioteca.model.Partner;
import com.mycompany.biblioteca.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ILoan implements Repository<Loan, Integer> {

    @Override
    public Loan create(Loan loan) {
        String sql = "INSERT INTO loan (id_book, id_partner, delivery_date, return_date, returned) VALUES (?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, loan.getBook().getId());
            ps.setInt(2, loan.getPartner().getId());
            ps.setDate(3, Date.valueOf(loan.getDeliveryDate()));

            if (loan.getReturnDate() != null) {
                ps.setDate(4, Date.valueOf(loan.getReturnDate()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setBoolean(5, loan.isReturned());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    loan.setId(rs.getInt(1));
                }
            }

            return loan;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al crear préstamo: " + e.getMessage());
        }
    }

    @Override
    public Loan searchById(Integer id) {
        String sql = "SELECT l.*, "
                + "b.title, b.author, b.isbn, b.stock, "
                + "p.name as partner_name "
                + "FROM loan l "
                + "INNER JOIN books b ON l.id_book = b.id "
                + "INNER JOIN partner p ON l.id_partner = p.id "
                + "WHERE l.id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Loan loan = new Loan();
                    loan.setId(rs.getInt("id"));
                    loan.setIdBook(rs.getInt("id_book"));
                    loan.setIdPartner(rs.getInt("id_partner"));
                    loan.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());

                    if (rs.getDate("return_date") != null) {
                        loan.setReturnDate(rs.getDate("return_date").toLocalDate());
                    }
                    loan.setReturned(rs.getBoolean("returned"));

                    // Crear y asignar Book
                    Book book = new Book();
                    book.setId(rs.getInt("id_book"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setStock(rs.getInt("stock"));
                    loan.setBook(book);

                    // Crear y asignar Partner
                    Partner partner = new Partner();
                    partner.setId(rs.getInt("id_partner"));
                    partner.setName(rs.getString("partner_name"));
                    loan.setPartner(partner);

                    return loan;
                } else {
                    throw new ResourceNotFound("No se encontro el prestamo con este ID" + id);

                }
            }

            

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al buscar préstamo: " + e.getMessage());
        }
    }

    @Override
    public List<Loan> searchAll() {
        String sql = "SELECT l.*, "
                + "b.title, b.author, b.isbn, b.stock, "
                + "p.name as partner_name "
                + "FROM loan l "
                + "INNER JOIN books b ON l.id_book = b.id "
                + "INNER JOIN partner p ON l.id_partner = p.id";

        List<Loan> loans = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setId(rs.getInt("id"));
                loan.setIdBook(rs.getInt("id_book"));
                loan.setIdPartner(rs.getInt("id_partner"));
                loan.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());

                if (rs.getDate("return_date") != null) {
                    loan.setReturnDate(rs.getDate("return_date").toLocalDate());
                }
                loan.setReturned(rs.getBoolean("returned"));

                // CREAR los objetos Book y Partner PRIMERO
                Book book = new Book();
                book.setId(rs.getInt("id_book"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setStock(rs.getInt("stock"));
                loan.setBook(book);  // AHORA sí asignar

                Partner partner = new Partner();
                partner.setId(rs.getInt("id_partner"));
                partner.setName(rs.getString("partner_name"));
                loan.setPartner(partner);  // AHORA sí asignar

                loans.add(loan);
            }
            return loans;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al obtener préstamos: " + e.getMessage());
        }
    }

    @Override
    public Loan update(Loan loan) {
        String sql = "UPDATE loan SET id_book = ?, id_partner = ?, delivery_date = ?, return_date = ?, returned = ? WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getIdBook());
            ps.setInt(2, loan.getIdPartner());
            ps.setDate(3, Date.valueOf(loan.getDeliveryDate()));

            if (loan.getReturnDate() != null) {
                ps.setDate(4, Date.valueOf(loan.getReturnDate()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setBoolean(5, loan.isReturned());
            ps.setInt(6, loan.getId());

            ps.executeUpdate();
            return loan;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al actualizar préstamo: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM loan WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al eliminar préstamo: " + e.getMessage());
        }
    }
}
