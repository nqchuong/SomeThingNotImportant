/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.UserDAO;
import chuongnq.dtos.UserDTO;
import chuongnq.dtos.UserErrorDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class LoginServlet extends HttpServlet {

    private static final String SUB = "subPage.jsp";
    private static final String ADMIN = "adminPage.jsp";
    private static final String LOGINPAGE = "login.jsp";

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
        String url = LOGINPAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                boolean valid = true;
                String userid = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                UserErrorDTO loginErr = new UserErrorDTO();
                if (userid.trim().length() <= 0) {
                    valid = false;
                    loginErr.setUsernameError("UserId not null");
                }
                if (password.trim().length() <= 0) {
                    valid = false;
                    loginErr.setPasswordError("Password not null");
                }
                //check login
                if (valid == true) {
                    //login success
                    UserDAO dao = new UserDAO();
                    UserDTO dto = dao.checkLogin(userid, password);
                    List<UserDTO> dtoUser = new ArrayList<>();
                    if (dto != null) {
                        //check role de chuyen trang 
                        if (dto.status.equals("Inactive")) {
                            url = LOGINPAGE;
                            request.setAttribute("MESSAGE123", "Your account is Banned!!");
                        } else if (dto.role.equals("Sub")) {//Role Sub
                            url = SUB;
                            session.setAttribute("USERINFOR", dto);
                        } else if (dto.role.equals("Admin")) {//Role admin
                            url = ADMIN;
                            //   dtoUser = dao.loadUserActive();
                            session.setAttribute("USERINFOR", dto);
                            //   session.setAttribute("USERACTIVE", dtoUser);
//                        request.setAttribute("USERACTIVE", dtoUser);
                        }
                    } else {
                        loginErr.setPasswordError("Password not correct");
                        request.setAttribute("LOGINERROR", loginErr);
                        url = LOGINPAGE;
                    }
                } else {// login failed
                    url = LOGINPAGE;
                    request.setAttribute("LOGINERROR", loginErr);
                }
            } else {
                url = LOGINPAGE;
                request.setAttribute("MESSAGE", "You need login!!");
            }
        } catch (Exception e) {
            log("ERROR at LoginServlet: " + e.getMessage());
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
