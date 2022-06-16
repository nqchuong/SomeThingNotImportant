/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.dtos;

import java.io.Serializable;

/**
 *
 * @author CHUONG
 */
public class UserDTO implements Serializable {

    public String username, password, role, phone, status, photo, name, email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO(String username, String password, String role, String phone, String status, String photo, String name, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.status = status;
        this.photo = photo;
        this.name = name;
        this.email = email;
    }

    public UserDTO(String username, String role, String phone, String status, String photo, String name, String email) {
        this.username = username;
        this.role = role;
        this.phone = phone;
        this.status = status;
        this.photo = photo;
        this.name = name;
        this.email = email;
    }

}
