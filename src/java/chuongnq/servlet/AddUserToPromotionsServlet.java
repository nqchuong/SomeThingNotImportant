/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.PromotionDAO;
import chuongnq.dtos.AddUserToPromotionDTO;
import chuongnq.dtos.PromotionDTO;
import chuongnq.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddUserToPromotionsServlet extends HttpServlet {

    private final String LOGINPAGE = "login.jsp";
    private final String ROLLBACK = "ProcessServlet?txtSearchUser=&btAction=Search";

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
        String url = ROLLBACK;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USERINFOR");
                if (user != null) {
                    String username = request.getParameter("cid");
                    String status = "Active";
                    int rank = 5;
                    List<AddUserToPromotionDTO> listPromo = (List<AddUserToPromotionDTO>) session.getAttribute("LISTUSERPROMO");
                    List<PromotionDTO> checkUser = new ArrayList<>();
                    PromotionDAO dao = new PromotionDAO();
                    checkUser = dao.checkUser(username);
                    if (listPromo == null) {
                        listPromo = new ArrayList<>();
                    }
                    AddUserToPromotionDTO dto = new AddUserToPromotionDTO(username, status, rank);
                    boolean found = false;
                    if (checkUser.size() > 0) {
                        request.setAttribute("LISTPROMOMESSAGE", "This user had to add already!!");
                        found = true;
                        url = ROLLBACK;
                    }
                    if (listPromo.size() > 0) {
                        for (int i = 0; i < listPromo.size(); i++) {
                            if (listPromo.get(i).getUsername().equals(username)) {
                                request.setAttribute("LISTPROMOMESSAGE", "This user had to add already!!");
                                found = true;
                                url = ROLLBACK;
                            }
                        }
                    }
                    if (!found) {
                        listPromo.add(dto);
                        request.setAttribute("LISTPROMOMESSAGE", "");
                    }
                    session.setAttribute("LISTUSERPROMO", listPromo);
                    request.setAttribute("LISTUSERPROMO", listPromo);
                    url = ROLLBACK;
                    for (int i = 0; i < listPromo.size(); i++) {
                        System.out.println("username: " + listPromo.get(i).getUsername());
                        System.out.println("status: " + listPromo.get(i).getStatus());
                        System.out.println("rank: " + listPromo.get(i).getRank());
                    }
                }
            } else {
                url = LOGINPAGE;
            }
        } catch (Exception e) {
            log("ERROR at AddUserToPromotionsServlet: " + e.getMessage());
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
