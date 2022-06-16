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
public class AddUserToPromotionDTO implements Serializable {

    String username, status;
    int rank;

    public AddUserToPromotionDTO() {
    }

    public AddUserToPromotionDTO(String username, String status, int rank) {
        this.username = username;
        this.status = status;
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
