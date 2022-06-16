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
public class UpdatePasswordDTO implements Serializable {

    String oldPsw, newPsw, confirmPsw;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String oldPsw, String newPsw, String confirmPsw) {
        this.oldPsw = oldPsw;
        this.newPsw = newPsw;
        this.confirmPsw = confirmPsw;
    }

    public String getOldPsw() {
        return oldPsw;
    }

    public void setOldPsw(String oldPsw) {
        this.oldPsw = oldPsw;
    }

    public String getNewPsw() {
        return newPsw;
    }

    public void setNewPsw(String newPsw) {
        this.newPsw = newPsw;
    }

    public String getConfirmPsw() {
        return confirmPsw;
    }

    public void setConfirmPsw(String confirmPsw) {
        this.confirmPsw = confirmPsw;
    }

}
