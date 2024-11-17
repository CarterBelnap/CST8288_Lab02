package com.indyDAO;

import com.indyDTO.IndyWinnerDTO;
import com.dbconn.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IndyWinnerDAO interface using JDBC.
 */
public class ConcreteIndyWinnerDAO implements IndyWinnerDAO {

    @Override
    public List<IndyWinnerDTO> getAllWinners(int offset, int limit) {
        List<IndyWinnerDTO> winners = new ArrayList<>();
        String query = "SELECT * FROM IndyWinners ORDER BY year DESC LIMIT ? OFFSET ?";

        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    winners.add(new IndyWinnerDTO(
                            rs.getInt("year"),
                            rs.getString("driver"),
                            rs.getFloat("averagespeed"),
                            rs.getString("country")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return winners;
    }
}
