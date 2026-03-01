package ma.bank.dao;



import ma.bank.model.Client;
import ma.bank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void save(Client c) {
        String sql = "INSERT INTO client(nom, categorie, ville) VALUES (?, ?, ?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getNom());
            ps.setString(2, c.getCategorie());
            ps.setString(3, c.getVille());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Client c) {
        String sql = "UPDATE client SET nom = ?, categorie = ?, ville = ? WHERE id = ?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getNom());
            ps.setString(2, c.getCategorie());
            ps.setString(3, c.getVille());
            ps.setInt(4, c.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Client> findAll() {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("categorie"),
                        rs.getString("ville")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Client findById(int id) {

        String sql = "SELECT * FROM client WHERE id = ?";
        Client client = null;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("categorie"),
                        rs.getString("ville")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return client; // null si client inexistant
    }
    public List<Client> findByCategorie(String categorie) {

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client WHERE categorie = ?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categorie);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("categorie"),
                        rs.getString("ville")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
    public void delete(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}