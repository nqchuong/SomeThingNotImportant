/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.daos;

import chuongnq.db.MyConnection;
import chuongnq.dtos.AddUserToPromotionDTO;
import chuongnq.dtos.HistoryPromotionDTO;
import chuongnq.dtos.PromotionDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CHUONG
 */
public class PromotionDAO implements Serializable {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<PromotionDTO> checkUser(String username) throws SQLException, ClassNotFoundException {
        List<PromotionDTO> list = null;
        PromotionDTO dto = null;
        String checkname, status;
        int rank;
        try {
            String sql = "SELECT Username,Rank,Status from tbl_Promotions WHERE Username = ?";
            conn = MyConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            if (rs.next()) {
                checkname = rs.getString("Username");
                rank = rs.getInt("Rank");
                status = rs.getString("Status");
                dto = new PromotionDTO(checkname, status, rank);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean addUserToPromotion(AddUserToPromotionDTO dto) throws SQLException, ClassNotFoundException {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO tbl_Promotions(Id,Username,Rank,Status,CreateDate) "
                    + "VALUES(NEWID(),?,?,?,GETDATE())";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setInt(2, dto.getRank());
            stm.setString(3, dto.getStatus());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<HistoryPromotionDTO> viewHistory(String username) throws SQLException, ClassNotFoundException {
        List<HistoryPromotionDTO> list = null;
        HistoryPromotionDTO dto = null;
        String userid, promotionId, status;
        int rank;
        Date createDate;
        try {
            String sql = "SELECT Id,Username,Rank,Status,CreateDate "
                    + "FROM tbl_Promotions "
                    + "WHERE Username = ? ";
            conn = MyConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                userid = rs.getString("Username");
                promotionId = rs.getString("Id");
                rank = rs.getInt("Rank");
                status = rs.getString("Status");
                createDate = rs.getDate("CreateDate");
                dto = new HistoryPromotionDTO(promotionId, userid, status, rank, createDate);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
