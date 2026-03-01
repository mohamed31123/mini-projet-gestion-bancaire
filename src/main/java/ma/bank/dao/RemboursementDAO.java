package ma.bank.dao;



import ma.bank.util.DBConnection;
import java.sql.*;

public class RemboursementDAO {

    public void enregistrer(int pretId, double montant) {
        String sql = "INSERT INTO remboursement(pret_id, date, montant) VALUES (?, CURDATE(), ?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, pretId);
            ps.setDouble(2, montant);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double totalRembourseParPret(int pretId) {
        String sql = "SELECT IFNULL(SUM(montant),0) total FROM remboursement WHERE pret_id = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, pretId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("total");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
