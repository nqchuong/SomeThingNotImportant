/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.UserDAO;
import chuongnq.dtos.CheckPhotoDTO;
import chuongnq.dtos.UpdateUserDTO;
import chuongnq.dtos.UserDTO;
import chuongnq.dtos.UserErrorDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author CHUONG
 */
public class UpdateUserServlet extends HttpServlet {

    private final String ERROR = "updateInfor.jsp";
    private final String SUCCESS = "updateInfor.jsp";
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
                    String name = request.getParameter("txtName");
                    String email = request.getParameter("txtEmail");
                    String phone = request.getParameter("txtPhone");
                    String username = user.getUsername();

                    //hinh anh 
                    Part part = request.getPart("file");
                    //String fileName = part.getSubmittedFileName();
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String path = getServletContext().getRealPath("/").replace("\\build\\web", "") + "web";//thay doi duong dan file
                    String realpath = path + "\\images";
                    System.out.println("Path: " + realpath);
                    System.out.println("File Name " + fileName);

                    //pattern check email
                    String checkEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                    Pattern patternEmail = Pattern.compile(checkEmail);
                    Matcher matchEmail = patternEmail.matcher(email);
                    //paattern check phone
                    String checkPhone = "^[0-9]{1,15}$";
                    Pattern patternPhonne = Pattern.compile(checkPhone);
                    Matcher matchPhone = patternPhonne.matcher(phone);
                    UserErrorDTO updateUserError = new UserErrorDTO();
                    boolean valid = true;
                    //check update user
                    if (name.trim().length() <= 0) {
                        valid = false;
                        updateUserError.setNameError("Name not null");
                    }
                    if (phone.trim().length() <= 0) {
                        valid = false;
                        updateUserError.setPhoneError("Phone not null");
                    }
                    if (email.trim().length() <= 0) {
                        valid = false;
                        updateUserError.setEmailError("Email not null");
                    }
                    if (matchEmail.matches() == false) {
                        valid = false;
                        updateUserError.setEmailError("Email must be abc@gmail.com");
                    }
                    if (matchPhone.matches() == false) {
                        valid = false;
                        updateUserError.setPhoneError("Phone example: 0912345687");
                    }
                    if (fileName.trim().length() <= 0) {
                        fileName = user.getPhoto();
                    }
                    //Update User
                    if (valid == true) {
                        UserDAO dao = new UserDAO();
                        UpdateUserDTO dto = new UpdateUserDTO(name, fileName, phone, email);
                        List<CheckPhotoDTO> checkImage = dao.checkImage(fileName);
                        if (checkImage.size() <= 0) {
                            InputStream is = part.getInputStream();
                            //uploadImage(is, realpath);
                            Files.copy(is, Paths.get(realpath));
                        }
                        boolean result = dao.updateUser(dto, username);
                        if (result) {
                            url = SUCCESS;
                            request.setAttribute("MESSAGE", "Update User Successful");
                        }
                        List<UserDTO> reloadUser = dao.reloadUser(username);
                        session.setAttribute("USERINFOR", reloadUser);
                    } else {
                        url = ERROR;
                        request.setAttribute("UPDATEUSERERROR", updateUserError);
                    }
                }
            } else {
                url = LOGINPAGE;
            }
        } catch (Exception e) {
            log("ERROR at UpdateUserServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private boolean uploadImage(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        } catch (Exception e) {
            log("ERROR at UpdateUserServlet - uploadImage: " + e.getMessage());
        }
        return test;
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
