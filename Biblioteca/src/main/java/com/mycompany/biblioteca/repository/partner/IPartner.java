package com.mycompany.biblioteca.repository.partner;

import com.mycompany.biblioteca.db.Conexion;
import com.mycompany.biblioteca.exeptions.ErrorSystemException;
import com.mycompany.biblioteca.model.Partner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IPartner implements PartnerRepository {

    @Override
    public Partner create(Partner partner) {
        String sql = "INSERT INTO partner (name_) VALUES (?)";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, partner.getName());

            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    partner.setId(rs.getInt(1));
                }
            }

            return partner;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al crear socio: " + e.getMessage());
        }
    }

    @Override
    public Partner searchById(Integer id) {
        String sql = "SELECT * FROM partner WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Partner partner = new Partner();
                    partner.setId(rs.getInt("id"));
                    partner.setName(rs.getString("name_"));

                    return partner;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al buscar socio: " + e.getMessage());
        }
    }

    @Override
    public List<Partner> searchAll() {
        String sql = "SELECT * FROM partner";
        List<Partner> partners = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Partner partner = new Partner();
                partner.setId(rs.getInt("id"));
                partner.setName(rs.getString("name_"));

                partners.add(partner);
            }

            return partners;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al obtener socios: " + e.getMessage());
        }
    }

    @Override
    public Partner update(Partner partner) {
        String sql = "UPDATE partner SET name = ?, document_number = ? WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, partner.getName());

            ps.setInt(2, partner.getId());

            ps.executeUpdate();
            return partner;

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al actualizar socio: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM partner WHERE id = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ErrorSystemException("Error al eliminar socio: " + e.getMessage());
        }
    }
}
