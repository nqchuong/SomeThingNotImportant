/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuongnq.servlet;

import chuongnq.daos.UserDAO;
import chuongnq.dtos.CheckPhotoDTO;
import chuongnq.dtos.UserDTO;
import chuongnq.dtos.UserErrorDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author CHUONG
 */
@MultipartConfig
public class CreateUserServlet extends HttpServlet {

    private static final String LOGINPAGE = "login.jsp";
    private static final String INVALID = "createNewAccount.jsp";
    private static final String SUCCESS = "createNewAccount.jsp";
    private static final String FAILED = "createNewAccount.jsp";

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
        String url = INVALID;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USERINFOR");
                if (user != null) {
                    String userid = request.getParameter("txtUsername");
                    String psw = request.getParameter("txtPassword");
                    String name = request.getParameter("txtName");
                    String phone = request.getParameter("txtPhone");
                    String email = request.getParameter("txtEmail");
                    //String photo = request.getParameter("txtPhoto");
                    String role = request.getParameter("txtRole");
                    String status = request.getParameter("txtStatus");
                    String confirmPassword = request.getParameter("txtConfirmPassword");
                    //hinh anh 
                    Part part = request.getPart("file");
                    //String fileName = part.getSubmittedFileName();
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String path = getServletContext().getRealPath("/").replace("\\build\\web", "") + "web";//thay doi duong dan file
                    String realpath = path + "\\images" + File.separator + fileName;
                    System.out.println("Path: " + realpath);
                    System.out.println("File Name " + fileName);

                    //check email
                    String checkEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                    Pattern patternEmail = Pattern.compile(checkEmail);
                    Matcher matchEmail = patternEmail.matcher(email);
                    //check phone
                    String checkPhone = "^[0-9]{1,15}$";
                    Pattern patternPhonne = Pattern.compile(checkPhone);
                    Matcher matchPhone = patternPhonne.matcher(phone);
                    UserErrorDTO createUserError = new UserErrorDTO();
                    boolean valid = true;
                    //check create user
                    if (userid.trim().length() <= 0) {
                        valid = false;
                        createUserError.setUsernameError("UserId not null");
                    }
                    if (psw.trim().length() <= 0) {
                        valid = false;
                        createUserError.setPasswordError("Password not null");
                    } else if (psw.trim().length() > 0 && confirmPassword.trim().length() <= 0) {
                        valid = false;
                        createUserError.setCofirmPasswordError("Confirm password not null");
                    } else if (!confirmPassword.trim().equals(psw.trim())) {
                        valid = false;
                        createUserError.setCofirmPasswordError("Confirm password not match");
                    }
                    if (name.trim().length() <= 0) {
                        valid = false;
                        createUserError.setNameError("Name not null");
                    }
                    if (phone.trim().length() <= 0) {
                        valid = false;
                        createUserError.setPhoneError("Phone not null");
                    }
                    if (email.trim().length() <= 0) {
                        valid = false;
                        createUserError.setEmailError("Email not null");
                    }
                    if (matchEmail.matches() == false) {
                        valid = false;
                        createUserError.setEmailError("Email must be abc@gmail.com");
                    }
                    if (matchPhone.matches() == false) {
                        valid = false;
                        createUserError.setPhoneError("Phone example: 0912345687");
                    }
//                    if (fileName.trim().length() <= 0) {
//                        fileName = "avatar_default.png";
//                    }
                    //create success
                    if (valid == true) {
                        UserDAO dao = new UserDAO();
                        List<CheckPhotoDTO> checkImage = dao.checkImage(fileName);
                        if (checkImage.size() <= 0) {
                            InputStream is = part.getInputStream();
                            //uploadImage(is, realpath);
                            Files.copy(is, Paths.get(realpath));
                        } else if (fileName.trim().length() <= 0) {
                            fileName = "avatar_default.png";
                        }
                        UserDTO dto = new UserDTO(userid, psw, role, phone, status, fileName, name, email);
                        if (dao.createUser(dto)) {
                            request.setAttribute("MESSAGE", "Create user successfull!!!");
                            url = SUCCESS;
                        } else {
                            url = FAILED;
                            request.setAttribute("MESSAGE", "Create user failed");
                        }
                    } else {
                        url = FAILED;
                        request.setAttribute("CREATEUSERERROR", createUserError);
                    }
                }
            } else {
                url = LOGINPAGE;
            }
        } catch (Exception e) {
            log("ERROR at CreateUserServlet: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                UserErrorDTO createUserError = new UserErrorDTO();
                createUserError.setUsernameError("User Id is existed");
                request.setAttribute("MESSAGE", createUserError);
                url = INVALID;
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

//    private boolean uploadImage(InputStream is, String path) {
//        boolean test = false;
//        try {
//            byte[] byt = new byte[is.available()];
//            is.read();
//            FileOutputStream fops = new FileOutputStream(path);
//            fops.write(byt);
//            fops.flush();
//            fops.close();
//            test = true;
//        } catch (Exception e) {
//            log("ERROR at CreateUserServlet - uploadImage: " + e.getMessage());
//        }
//        return test;
//    }
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
