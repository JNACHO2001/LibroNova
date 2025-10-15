package com.mycompany.biblioteca.repository.book;

import com.mycompany.biblioteca.db.Conexion;
import com.mycompany.biblioteca.exeptions.DuplicateExceptionRecord;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.exeptions.ResourceNotFound;
import com.mycompany.biblioteca.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IBook implements BookRepository {

    @Override
    public Book create(Book b) {
        String sql = "INSERT INTO books (title,author,isbn,stock) VALUES (?,?,?,?)";
        try (Connection conn = Conexion.getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getIsbn());
            ps.setInt(4, b.getStock());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    b.setId(rs.getInt(1));

                }

            }

            System.out.println("se creo");

            return b;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                                throw new DuplicateExceptionRecord("There is already a book with this title");

                

            } else {
                throw  new ErrorSystemException("cannot be created" + e.getMessage());
            }

        }

    }


    @Override
    public Book searchById(Integer id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("id"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setIsbn(rs.getString("isbn"));
                    b.setStock(rs.getInt("stock"));
                    return b;
                } else {
                    throw  new ResourceNotFound("No information could be found");
                }
            }

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al buscar el libro: " + e.getMessage());
        }
    }

    @Override
    public List<Book> searchAll() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setIsbn(rs.getString("isbn"));
                b.setStock(rs.getInt("stock"));
                books.add(b);
            }

            return books;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al obtener la lista de libros: " + e.getMessage());
        }
    }

    @Override
    public Book update(Book b) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, stock = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getIsbn());
            ps.setInt(4, b.getStock());
            ps.setInt(5, b.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                                    throw  new ResourceNotFound("No information could be found");

                
            }

            
            return b;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DuplicateExceptionRecord("There is already a book with this title");
            } else {
                throw new ErrorSystemException("Error updating the book" + e.getMessage());
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new ResourceNotFound("Book with id not found :" + id);

            }

        } catch (SQLException e) {
            throw new ErrorSystemException("Error deleting book:" + e.getMessage());
        }
    }

}
