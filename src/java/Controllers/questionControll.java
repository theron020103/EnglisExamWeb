/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.questionDao;
import Dao.quizDao;
import Dao.userDao;
import Model.Question;
import Model.Selects;
import Model.result;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
@WebServlet(name = "questionControll", urlPatterns = {"/question"})
public class questionControll extends HttpServlet {

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
            out.println("<title>Servlet questionControll</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet questionControll at " + request.getContextPath() + "</h1>");
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
        HttpSession sess = request.getSession();
        String acc = (String) sess.getAttribute("login");
        if (acc == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (sess.getAttribute("making") == null) {
            String mess = "Your exam has been submitted, take new exam";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("eror.jsp").forward(request, response);
        }

        String code = request.getParameter("code");
        if(code==null || code.isEmpty()){
            code="ramdom";
        }
        questionDao q = new questionDao();
        userDao a = new userDao();
        quizDao b = new quizDao();

        ArrayList<Question> data = new ArrayList<>();
        if(request.getParameter("data")==null){
            data = q.getQuizByCode(code);
        }else{
            data = (ArrayList<Question>) request.getSession().getAttribute("data");
        }
            
         
        ArrayList<Selects> result = new ArrayList<>();
        int count = data.size();
        int correct = 0;

        for (Question u : data) {
            if (request.getParameter(u.getId()) == null) {
                Selects s = new Selects(u.getCorrect(), null, false, u);
                result.add(s);
                continue;
            }

            String option = request.getParameter(u.getId());
            boolean check = q.checkResult(option, u.getCorrect());
            if (check) {
                correct++;
            }
            Selects s = new Selects(u.getCorrect(), option, check, u);
            result.add(s);

        }
        double point = 10.0 * correct / count;
        point = Math.round(point * 100.0) / 100.0;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);
        String id_taker = a.getIdByAccount(acc);
        result r = new result(id_taker, code, correct, date, time, point);
        q.insert(r);
        sess.setAttribute("making", null);

        request.setAttribute("result", result);
        request.setAttribute("r", r);
        request.setAttribute("count", count);
        request.setAttribute("name", b.getNamebyCode(code));
        request.getRequestDispatcher("result.jsp").forward(request, response);
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
        HttpSession sess = request.getSession();
        String acc = (String) sess.getAttribute("login");
        if (acc == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (request.getParameter("count") == null) {
            String code = request.getParameter("code");
            questionDao q = new questionDao();
            ArrayList<Question> data = q.getQuizByCode(code);

            sess.setAttribute("making", code);
            request.setAttribute("data", data);
            request.setAttribute("code", code);
            request.getRequestDispatcher("quiz.jsp").forward(request, response);
        } else {
            String count = request.getParameter("count");
            questionDao q = new questionDao();
            ArrayList<Question> data = q.getQuizRamdom(count);
            
            sess.setAttribute("making", count);
            request.getSession().setAttribute("data", data);
            request.setAttribute("data", data);
            request.setAttribute("count", count);
            request.getRequestDispatcher("quiz.jsp").forward(request, response);
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
