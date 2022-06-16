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
public class UpdatePswErrorDTO implements Serializable {

    String pswNewErr, pswOldErr, pswConfirmErr;

    public UpdatePswErrorDTO() {
    }
    
    public String getPswNewErr() {
        return pswNewErr;
    }

    public void setPswNewErr(String pswNewErr) {
        this.pswNewErr = pswNewErr;
    }

    public String getPswOldErr() {
        return pswOldErr;
    }

    public void setPswOldErr(String pswOldErr) {
        this.pswOldErr = pswOldErr;
    }

    public String getPswConfirmErr() {
        return pswConfirmErr;
    }

    public void setPswConfirmErr(String pswConfirmErr) {
        this.pswConfirmErr = pswConfirmErr;
    }

}
