/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CHUONG
 */
public class ProcessServlet extends HttpServlet {

    public static final String LOGIN = "LoginServlet";
    public static final String CREATEUSER = "CreateUserServlet";
    public static final String LOGOUT = "LogoutServlet";
    public static final String UPDATEUSER = "UpdateUserServlet";
    public static final String DELETEUSER = "DeleteUserServlet";
    public static final String SEARCHUSER = "SearchUserServlet";
    public static final String UPDATEPSWUSER = "UpdatePasswordServlet";
    public static final String REMOVEUSER = "RemoveUserFromListServlet";
    public static final String ADDTOPROMOTION = "AddUserToPromotionsServlet";
    public static final String ADDUSERPROMO = "AddListUserToDBServlet";
    public static final String HISTORYPROMOTION = "ViewHistoryPromotionServlet";

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
        String url = LOGIN;
        try {
            HttpSession session = null;
            session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("USER");
            String action = request.getParameter("btAction");

            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Create User")) {
                url = CREATEUSER;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Update User")) {
                url = UPDATEUSER;
            } else if (action.equals("Delete")) {
                url = DELETEUSER;
            } else if (action.equals("Search")) {
                url = SEARCHUSER;
            } else if (action.equals("Update")) {
                url = UPDATEUSER;
            } else if (action.equals("Change Password")) {
                url = UPDATEPSWUSER;
            } else if (action.equals("Remove")) {
                url = REMOVEUSER;
            } else if (action.equals("Add to Promotion")) {
                url = ADDTOPROMOTION;
            } else if (action.equals("Add")) {
                url = ADDUSERPROMO;
            } else if (action.equals("View History Promotion")) {
                url = HISTORYPROMOTION;
            } else {
                request.setAttribute("ERROR", "This function is not supported");
            }
        } catch (Exception e) {
            log("ERROR at ProcessServle" + e.getMessage());
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
