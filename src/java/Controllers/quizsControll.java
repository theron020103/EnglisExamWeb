/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.questionDao;
import Dao.quizDao;
import Dao.userDao;
import Model.Question;
import Model.Quizs;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet(name = "quizsControll", urlPatterns = {"/quiz"})
public class quizsControll extends HttpServlet {

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
            out.println("<title>Servlet quizController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet quizController at " + request.getContextPath() + "</h1>");
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
            String mod = request.getParameter("mod");
            request.setAttribute("log", mod);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        questionDao u = new questionDao();
        userDao d = new userDao();
        quizDao q = new quizDao();
        if (request.getParameter("mod") == null) {
            ArrayList<Quizs> data = q.getQuizsStu(acc);
            request.setAttribute("data", data);
            request.getRequestDispatcher("listQuizs.jsp").forward(request, response);
        } else if (request.getParameter("mod") != null && request.getParameter("count") == null) {
            String code = request.getParameter("id");
            String name = q.getNamebyCode(code);
            if (name.equals("")) {
                String mess = "Code does not exist!";
                request.setAttribute("code", code);
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
            }
            request.setAttribute("name", name);
            request.setAttribute("code", code);
            if (d.checkAmin(acc)) {
                ArrayList<Question> data = u.getQuizByCode(code);
                request.setAttribute("data", data);
                request.getRequestDispatcher("viewQuiz.jsp").forward(request, response);
            }
            request.getRequestDispatcher("start.jsp").forward(request, response);
        } else {
            String count = request.getParameter("count");
            int sum = u.getSumQuestion();
            if (!u.checkCount(count)) {
                String mess2 = "Enter a number from 1 to" + sum;
                request.setAttribute("mess2", mess2);
                request.setAttribute("count", count);
                request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
            } else {
                request.setAttribute("name", "ramdom");
                request.setAttribute("code", "ramdom");
                request.setAttribute("count", count);
                request.getRequestDispatcher("start.jsp").forward(request, response);
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        String acc = (String) sess.getAttribute("login");
        if (acc == null) {
            String mod = request.getParameter("mod");
            request.setAttribute("log", mod);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (request.getParameter("CONTINUE") != null) {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            quizDao q = new quizDao();
            if (code.trim().isEmpty() || name.trim().isEmpty()) {
                String mess = "Input can not blank!";
                request.setAttribute("code", code);
                request.setAttribute("mess", mess);
                request.setAttribute("name", name);
                request.getRequestDispatcher("create.jsp").forward(request, response);
            }
            String namex = q.getNamebyCode(code);
            if (!namex.equals("")) {
                String mess = "Code exist!";
                request.setAttribute("code", code);
                request.setAttribute("mess", mess);
                request.setAttribute("name", name);
                request.getRequestDispatcher("create.jsp").forward(request, response);
            }

            request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.getRequestDispatcher("createQuiz.jsp").forward(request, response);

        } else {

            String code = request.getParameter("code");
            String name = request.getParameter("name");

            userDao u = new userDao();
            quizDao d = new quizDao();
            String id = u.getIdByAccount(acc);
            int index = 1;
            ArrayList<Question> q = new ArrayList<>();

            // Loop through each question and its options
            while (true) {
                String question = request.getParameter("question" + index);
                if (question == null) {
                    break; // Exit the loop if no more questions are found
                }
                String answer = request.getParameter("answer" + index);

                // Collect options for the current question
                List<String> optionList = new ArrayList<>();
                int optionIndex = 1;
                while (true) {
                    String option = request.getParameter("option" + index + "_" + optionIndex);
                    if (option == null) {
                        break; // Exit the loop if no more options are found
                    }
                    optionList.add(option);
                    optionIndex++;
                }

                // Convert optionList to array
                String[] options = optionList.toArray(new String[0]);

                // Create a Question object and add it to the list
                Question qItem = new Question(String.valueOf(index), code, question, answer, options);
                q.add(qItem);
                index++;
            }

            // Insert the quiz into the database
            d.insertQuiz(code, name, q, id);

            request.setAttribute("name", name);
            request.setAttribute("code", code);
            request.getRequestDispatcher("start.jsp").forward(request, response);
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
