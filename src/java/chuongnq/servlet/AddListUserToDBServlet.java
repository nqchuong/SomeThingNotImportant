/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.PromotionDAO;
import chuongnq.dtos.AddUserToPromotionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CHUONG
 */
public class AddListUserToDBServlet extends HttpServlet {

    private final String SUCCESS = "adminPage.jsp";
    private final String FAILED = "listOfPromotions.jsp";

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
            String status = "Active";
            List<AddUserToPromotionDTO> list = (List<AddUserToPromotionDTO>) session.getAttribute("LISTUSERPROMO");
            String[] rank = request.getParameterValues("txtRank");
            request.setAttribute("RANKERROR", "");
            //check rank 
            boolean valid = false;
            for (int i = 0; i < rank.length; i++) {
                String checkRank = "^[0-9]{1,10}$";
                Pattern patternRank = Pattern.compile(checkRank);
                Matcher matchRank = patternRank.matcher(rank[i]);
                if (matchRank.matches() == false) {
                    valid = true;
                }
            }
            if (valid == false) {
                PromotionDAO dao = new PromotionDAO();
                boolean checkRank2 = false;
                for (int i = 0; i < rank.length; i++) {
                    if (Integer.parseInt(rank[i]) > 11) {
                        checkRank2 = true;
                    } else if (Integer.parseInt(rank[i]) < 0) {
                        checkRank2 = true;
                    }
                }
                if (checkRank2 == true) {
                    request.setAttribute("RANKERROR", "Rank must be > 0 and < 11");
                    url = FAILED;
                } else {
                    for (int i = 0; i < rank.length; i++) {
                        AddUserToPromotionDTO dto = new AddUserToPromotionDTO(list.get(i).getUsername(), status, Integer.parseInt(rank[i]));
                        dao.addUserToPromotion(dto);
                    }
                    url = SUCCESS;
                    session.removeAttribute("LISTUSERPROMO");
                }
            } else if (valid == true) {
                request.setAttribute("RANKERROR", "Rank must be Integer");
                url = FAILED;
            }
        } catch (Exception e) {
            log("ERROR at AddListUserToDBServlet: " + e.getMessage());
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
