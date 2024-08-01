/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import Dao.quizDao;
import Dao.resultDao;
import Dao.userDao;
import Model.rePrint;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ACER
 */
public class resultControll extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet resultControll</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resultControll at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession sess = request.getSession();
        String acc = (String) sess.getAttribute("login");
        if(acc==null){
            String mod = request.getParameter("mod");
            request.setAttribute("log", mod);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        userDao u = new userDao();
        resultDao r = new resultDao();
        quizDao q = new quizDao();
        
        String id = u.getIdByAccount(acc);
        ArrayList<rePrint> data = r.getRePrintById(id);
        Collections.sort(data, new Comparator<rePrint>() {
        public int compare(rePrint a, rePrint b) {
        // So sánh ngày trước
            int dateComparison = a.getDate().compareTo(b.getDate());
            if (dateComparison != 0) {
                return dateComparison;
            }
        // Nếu ngày bằng nhau, so sánh thời gian, nhưng đảo ngược kết quả
           return -a.getTime().compareTo(b.getTime());
        }
        });
        
        request.setAttribute("data", data);
        request.getRequestDispatcher("ListResult.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
