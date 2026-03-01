package ma.bank.dao;



import ma.bank.util.DBConnection;
import java.sql.*;

public class PretDAO {

    public void accorderPret(int clientId, double montant, double taux) {
        String sql = "INSERT INTO pret(client_id, montant, taux, date_debut) VALUES (?, ?, ?, CURDATE())";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, clientId);
            ps.setDouble(2, montant);
            ps.setDouble(3, taux);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double encoursClient(int clientId) {
        String sql =
                "SELECT SUM(p.montant) - IFNULL(SUM(r.montant),0) encours " +
                        "FROM pret p " +
                        "LEFT JOIN remboursement r ON p.id = r.pret_id " +
                        "WHERE p.client_id = ?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("encours");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public double encoursClientByPret(int pretId) {
        String sql =
                "SELECT p.montant - IFNULL(SUM(r.montant),0) encours " +
                        "FROM pret p LEFT JOIN remboursement r ON p.id = r.pret_id " +
                        "WHERE p.id = ? GROUP BY p.id";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, pretId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("encours");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
