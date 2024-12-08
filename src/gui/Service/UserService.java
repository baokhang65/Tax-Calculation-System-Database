package gui.Service;

import gui.SQLServerConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Kiểm tra tồn tại của user_id trong bảng Users
    public boolean isStaffExists(long staffId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM Staffs WHERE Staff_id = ?";
        try (Connection conn = SQLServerConnection.getConnection() ;
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, staffId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tổng thu nhập (grossIncome) từ bảng Income_Details dựa trên user_id
    public long getGrossIncome(long staffId) throws SQLException, ClassNotFoundException {
        String query = "SELECT amount FROM Income_Details WHERE Staff_id = ?";
        try (Connection conn = SQLServerConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, staffId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
