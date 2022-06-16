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
public class searchUserDTO implements Serializable {

    String name, photo, phone, email, username;

    public searchUserDTO() {
    }

    public searchUserDTO(String name, String photo, String phone, String email, String username) {
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
