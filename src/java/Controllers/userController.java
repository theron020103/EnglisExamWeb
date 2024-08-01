/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.userDao;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class userController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet userController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet userController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String log = request.getParameter("log");
        String err = "";

        userDao d = new userDao();
        boolean checked = d.checkUser(account, pass);

        HttpSession sess = request.getSession();
        if (request.getParameter("mod") != null) {
            sess.setAttribute("login", null);
            request.getRequestDispatcher("index.html").forward(request, response);
        }

        if (checked) {
            // lưu ttin login vào setion
            sess.setAttribute("login", account);
            String userName = d.getNameByAccount(account);
            request.setAttribute("name", userName);
            if(log.equals("1")){
                request.getRequestDispatcher("quiz").forward(request, response);
            } else if(log.equals("3")){
                request.getRequestDispatcher("result").forward(request, response);
            } else if(log.equals("2")){
                request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
            } else if(log.equals("4")){
                request.getRequestDispatcher("create.jsp").forward(request, response);
            }
            
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            err = "account or password is incorrect";
            request.setAttribute("err", err);
            request.setAttribute("account", account);
            request.setAttribute("pass", pass);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

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
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String pass2 = request.getParameter("pass2");
        String name = request.getParameter("name");
        String err = "";
        String suces = "SIGNUP SUCCESS!";

        userDao d = new userDao();
        boolean checkName = d.checkName(name);
        boolean checked = d.checkAccount(account);
        boolean checkSignupEmpty = d.checkSignupEmpty(name, account, pass, pass2);

        if (request.getParameter("mod") != null) {
            suces = null;
            request.setAttribute("suces", suces);
            request.getRequestDispatcher("index.html").forward(request, response);
        }

        if (checkName && !checked && pass.equals(pass2) && checkSignupEmpty) {
            Users u = new Users("", name, account, pass);
            d.insertUser(u);
            request.setAttribute("suces", suces);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (!checkName) {
                err = "Invalid name!";
            } else if (checked) {
                err = "Account already exists!";
            } else if (!pass.equals(pass2)) {
                err = "Passwords do not match!";
            } else if (!checkSignupEmpty) {
                err = "Information cannot be left blank";
            }
            request.setAttribute("err", err);
            request.setAttribute("account", account);
            request.setAttribute("name", name);
            request.setAttribute("pass", pass);
            request.setAttribute("pass2", pass2);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }

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
