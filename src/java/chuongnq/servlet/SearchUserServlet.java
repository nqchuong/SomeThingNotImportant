/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.UserDAO;
import chuongnq.dtos.UserDTO;
import chuongnq.dtos.searchUserDTO;
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
public class SearchUserServlet extends HttpServlet {

    private final String SUCCESS = "adminPage.jsp";
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
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);
            String searchUserByName = request.getParameter("txtSearchUser");
            request.setAttribute("SearchUser", searchUserByName);
            UserDAO dao = new UserDAO();
            List<searchUserDTO> result = dao.searchUser(searchUserByName);
            List<searchUserDTO> resultPaging = dao.searchUserPaging(searchUserByName, indexPage);
            int numberPage = dao.getNumberPageUser(searchUserByName);
            request.setAttribute("NUMBERPAGE", numberPage);
            request.setAttribute("SEARCHUSERPAGING", resultPaging);
            request.setAttribute("INDEXPAGE", indexPage);
            request.setAttribute("SEARCHUSER", result);
            if (result.size() <= 0) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Not have your search name");
            } else {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at SearchUserServlet: " + e.getMessage());
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
