/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.UserDAO;
import chuongnq.dtos.UpdatePasswordDTO;
import chuongnq.dtos.UpdatePswErrorDTO;
import chuongnq.dtos.UserDTO;
import chuongnq.util.ConvertSHA;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CHUONG
 */
public class UpdatePasswordServlet extends HttpServlet {

    private final String ERROR = "changePassword.jsp";
    private final String SUCCESS = "changePassword.jsp";
    private final String LOGINPAGE = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USERINFOR");
                if (user != null) {
                    String pswNew = request.getParameter("txtNewPassword");
                    String pswOld = request.getParameter("txtOldPassword");
                    String pswConfirm = request.getParameter("txtConfirmPassword");
                    String covertOldPass = ConvertSHA.toString(pswOld);
                    UpdatePswErrorDTO pswErr = new UpdatePswErrorDTO();
                    String userid = user.getUsername();
                    String getOldPassword = user.getPassword();
                    boolean valid = true;
                    if (pswNew.trim().length() <= 0) {
                        valid = false;
                        pswErr.setPswNewErr("Your new Password not empty");
                    }
                    if (pswOld.trim().length() <= 0) {
                        valid = false;
                        pswErr.setPswOldErr("Input your old password");
                    } else if (!covertOldPass.trim().equals(getOldPassword)) {
                        valid = false;
                        pswErr.setPswOldErr("Your old password incorrect");
                    }
                    if (pswConfirm.trim().length() <= 0) {
                        valid = false;
                        pswErr.setPswConfirmErr("Your confirm password not empty");
                    } else if (!pswConfirm.trim().equals(pswNew)) {
                        valid = false;
                        pswErr.setPswConfirmErr("Confirm password not match");
                    }
                    //update psw
                    if (valid == true) {
                        UserDAO dao = new UserDAO();
                        UpdatePasswordDTO dto = new UpdatePasswordDTO(pswOld, pswNew, pswConfirm);
                        boolean result = dao.updatePassword(dto, userid);
                        if (result) {
                            url = SUCCESS;
                            request.setAttribute("PSWUPDATEMESSAGE", "Update password success!");
                        }
                        List<UserDTO> reloadUser = dao.reloadUser(userid);
                        session.setAttribute("USERINFOR", reloadUser);
                    } else {
                        url = ERROR;
                        request.setAttribute("PSWUPDATEERROR", pswErr);
                    }
                }
            } else {
                url = LOGINPAGE;
            }
        } catch (Exception e) {
            log("ERROR at UpdatePasswordServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
