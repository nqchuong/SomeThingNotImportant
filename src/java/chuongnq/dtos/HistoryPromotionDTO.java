/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CHUONG
 */
public class HistoryPromotionDTO implements Serializable{
    String id,username,status;
    int rank;
    Date createDate;

    public HistoryPromotionDTO() {
    }

    public HistoryPromotionDTO(String id, String username, String status, int rank, Date createDate) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.rank = rank;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
