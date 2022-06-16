/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.daos;

import chuongnq.db.MyConnection;
import chuongnq.dtos.CheckPhotoDTO;
import chuongnq.dtos.UpdatePasswordDTO;
import chuongnq.dtos.UpdateUserDTO;
import chuongnq.dtos.UserDTO;
import chuongnq.dtos.searchUserDTO;
import chuongnq.util.ConvertSHA;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author CHUONG
 */
public class UserDAO implements Serializable {

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

    public boolean createUser(UserDTO dto) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        boolean check = false;
        try {
            String sql = "Insert into tbl_User(Email,Name,Password,Phone,Photo,Role,Status,Username) values(?,?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.email);
            stm.setString(2, dto.name);
            stm.setString(3, ConvertSHA.toString(dto.password));
            stm.setString(4, dto.phone);
            stm.setString(5, dto.photo);
            stm.setString(6, dto.role);
            stm.setString(7, dto.status);
            stm.setString(8, dto.username);
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public UserDTO checkLogin(String userid, String password) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String role, status, username, email, photo, phone, name, psw;
        UserDTO dto = null;
        try {
            String sql = "SELECT Role,Username,Email,Name,Status,Photo,Phone,Password "
                    + "FROM tbl_User WHERE Username= ? and Password = ?";
            conn = MyConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, userid);
            stm.setString(2, ConvertSHA.toString(password));
            rs = stm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
                status = rs.getString("Status");
                username = rs.getString("Username");
                email = rs.getString("Email");
                name = rs.getString("Name");
                photo = rs.getString("Photo");
                phone = rs.getString("Phone");
                psw = rs.getString("Password");
                dto = new UserDTO(username, psw, role, phone, status, photo, name, email);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<UserDTO> loadUserActive() throws SQLException, ClassNotFoundException {
        List<UserDTO> listUser = null;
        UserDTO dto = null;
        String userid, name, photo, phone, psw, email, status, role;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Username,Name,Photo,Password,Email,Status,Role,Phone "
                    + "FROM tbl_User "
                    + "WHERE Status = 'Active' and Role = 'Sub' ";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            listUser = new ArrayList<>();
            while (rs.next()) {
                userid = rs.getString("Username");
                name = rs.getString("Name");
                photo = rs.getString("Photo");
                phone = rs.getString("Phone");
                psw = rs.getString("Password");
                email = rs.getString("Email");
                status = rs.getString("Status");
                role = rs.getString("Role");
                dto = new UserDTO(userid, psw, role, phone, status, photo, name, email);
                listUser.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public List<UserDTO> loadAllUser(String userid) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        List<UserDTO> listUser = null;
        UserDTO dto = null;
        String username, name, photo, phone, email, status, role;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Username, Name,Photo,Email,Status,Role  "
                    + "FROM tbl_User "
                    + "WHERE Username NOT IN('?')";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userid);
            rs = stm.executeQuery();
            listUser = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                name = rs.getString("Name");
                photo = rs.getString("Photo");
                phone = rs.getString("Phone");
                email = rs.getString("Email");
                status = rs.getString("Status");
                role = rs.getString("Role");
                dto = new UserDTO(username, role, phone, status, photo, name, email);
                listUser.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listUser;
    }

    public boolean updateUser(UpdateUserDTO dto, String userid) throws SQLException, ClassNotFoundException {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE tbl_User Set Name=?,Phone=?,Photo=?,Email=? "
                    + "WHERE Username = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getName());
            stm.setString(2, dto.getPhone());
            stm.setString(3, dto.getPhoto());
            stm.setString(4, dto.getEmail());
            stm.setString(5, userid);
            int row = stm.executeUpdate();
            if (row > 0) {
                check = true;
                return check;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updatePassword(UpdatePasswordDTO dto, String userid) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE tbl_User Set Password=? "
                    + "WHERE Username = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, ConvertSHA.toString(dto.getNewPsw()));
            stm.setString(2, userid);
            int row = stm.executeUpdate();
            if (row > 0) {
                check = true;
                return check;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUser(String status, String userid) throws SQLException, SQLException, ClassNotFoundException {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE tbl_User set Status = ? "
                    + "WHERE Username=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, status);
            stm.setString(2, userid);
            int row = stm.executeUpdate();
            if (row > 0) {
                check = true;
                return check;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<searchUserDTO> searchUser(String name) throws SQLException, ClassNotFoundException {
        List<searchUserDTO> list = null;
        searchUserDTO dto = null;
        String nameofUser, phone, photo, email, username;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Name,Photo,Email,Phone,Username "
                    + "FROM tbl_User "
                    + "WHERE Status = 'Active' and Role = 'Sub' and Name like ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, '%' + name + '%');
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                nameofUser = rs.getString("Name");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                email = rs.getString("Email");
                dto = new searchUserDTO(nameofUser, photo, phone, email, username);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<UserDTO> reloadUser(String username) throws SQLException, ClassNotFoundException {
        List<UserDTO> list = null;
        UserDTO dto = null;
        String name, phone, photo, email, userid, role, status;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Name,Photo,Email,Phone,Username,Role,Status "
                    + "FROM tbl_User "
                    + "WHERE Username = '?' ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                userid = rs.getString("Username");
                name = rs.getString("Name");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                email = rs.getString("Email");
                role = rs.getString("Role");
                status = rs.getString("Status");
                dto = new UserDTO(userid, role, phone, status, photo, name, email);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<searchUserDTO> searchUserPaging(String name, int index) throws SQLException, ClassNotFoundException {
        List<searchUserDTO> list = null;
        searchUserDTO dto = null;
        String nameofUser, phone, photo, email, username;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT Name,Photo,Email,Phone,Username "
                    + "FROM tbl_User "
                    + "WHERE Status = 'Active' and Role = 'Sub' and Name like ? "
                    + "ORDER BY Username "
                    + "OFFSET ? ROWS "
                    + "FETCH FIRST 5 ROW ONLY";
            stm = conn.prepareStatement(sql);
            stm.setString(1, '%' + name + '%');
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                nameofUser = rs.getString("Name");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                email = rs.getString("Email");
                dto = new searchUserDTO(nameofUser, photo, phone, email, username);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<CheckPhotoDTO> checkImage(String photoname) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        List<CheckPhotoDTO> list = null;
        CheckPhotoDTO dto = null;
        String photo;
        try {
            String sql = "SELECT Photo FROM tbl_User WHERE Photo LIKE ?";
            conn = MyConnection.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, '%' + photoname + '%');
            list = new ArrayList<>();
            rs = stm.executeQuery();
            while (rs.next()) {
                photo = rs.getString("Photo");
                dto = new CheckPhotoDTO(photo);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countUserSub() throws SQLException, NamingException, ClassNotFoundException {
        try {
            conn = MyConnection.getConnection();
            String sql = "Select count(*) from tbl_User WHERE Role = 'Sub'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int getNumberPageUser(String username) throws SQLException, ClassNotFoundException {
        try {
            conn = MyConnection.getConnection();
            String sql = "Select count(*) from tbl_User WHERE Role = 'Sub' and Name LIKE ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, '%' + username + '%');
            rs = stm.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 5;
                if (total % 5 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

}
