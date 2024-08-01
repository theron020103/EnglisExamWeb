/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DAL.DBContext;
import Model.Question;
import Model.result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class questionDao extends DBContext {

    Connection cnn; ///kết nối DB
    PreparedStatement stm; //// thực hiện câu lệnh spl
    ResultSet rs; //lưu trữ và xử lý rồi lấy giá trị về
    userDao d = new userDao();

    public questionDao() {
        connectDB();
    }

    private void connectDB() {
        cnn = connection;
        if (cnn != null) {
            System.out.println("Connect Sucess!");
        } else {
            System.out.println("Connect fail!");
        }
    }

    public ArrayList<Question> getQuizByCode(String code) {
        ArrayList<Question> data = new ArrayList<>();

        try {
            String strSQL = "select * from questions";
            stm = cnn.prepareStatement(strSQL);

            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String id_quiz = rs.getString(2);
                String content = rs.getString(3);
                String correct = rs.getString(4);
                String[] select = rs.getString(5).split(",");

                if (id_quiz.equals(code)) {
                    Question q = new Question(id, id_quiz, content, correct, select);
                    data.add(q);
                }
            }
        } catch (Exception e) {
            System.out.println("getQuizsStu:" + e.getMessage());
        }
        return data;
    }

    public boolean checkResult(String option, String answer) {
        if (option.equals(answer)) {
            return true;
        }
        return false;
    }

    public int getNumOfQuesByCode(String code) {
        try {
            String strSQL = "SELECT COUNT(id_quiz) AS count FROM questions WHERE id_quiz = ?";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, code);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("getNamebyCode:" + e.getMessage());
        }
        return 0;
    }

    public void insert(result r) {
        try {
            String strSQL = "insert into results values(?,?,?,?,?,?)";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, r.getId_taker());
            stm.setString(2, r.getId_quiz());
            stm.setInt(3, r.getNumOfCorrect());
            stm.setString(4, r.getDate().toString());
            stm.setString(5, r.getTime().toString());
            stm.setString(6, String.valueOf(r.getPoint()));
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("checkUser:" + e.getMessage());
        }
    }

    public void insertQuestion(Question q) {
        try {
            String strSQL = "insert into questions(id, id_quiz, content, correct, selected) values(?,?,?,?,?)";
            stm = cnn.prepareStatement(strSQL);
            stm.setString(1, q.getId());
            stm.setString(2, q.getId_quiz());
            stm.setString(3, q.getContent());
            stm.setString(4, q.getCorrect());
            String option = "";
            for (String s : q.getSelect()) {
                option = option + s + ",";
            }
            option = option.substring(0, option.length() - 1);
            stm.setString(5, option);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("insertQuestion:" + e.getMessage());
        }
    }

    public int getSumQuestion() {
        try {
            String strSQL = "SELECT COUNT(*) AS TotalQuestions\n"
                    + "FROM questions;";
            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalQuestions");
            }
        } catch (Exception e) {
            System.out.println("getSumQuestion:" + e.getMessage());
        }
        return -1;
    }

    public boolean checkCount(String count) {
        try {
            int x = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            return false;
        }
        int x = Integer.parseInt(count);
        int sumQuestion = getSumQuestion();
        if (x < 1 || x > sumQuestion) {
            return false;
        }
        return true;
    }

    public ArrayList<Question> getQuizRamdom(String count) {
        ArrayList<Question> data = new ArrayList<>();

        try {
            // Chuyển đổi count từ String sang int
            int limit = Integer.parseInt(count);

            // Sử dụng limit trong câu truy vấn SQL
            String strSQL = "SELECT TOP " + limit + " * FROM questions ORDER BY NEWID();";

            stm = cnn.prepareStatement(strSQL);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String id_quiz = rs.getString(2);
                String content = rs.getString(3);
                String correct = rs.getString(4);
                String[] select = rs.getString(5).split(",");
                Question q = new Question(id, id_quiz, content, correct, select);
                data.add(q);
            }
        } catch (Exception e) {
            System.out.println("getQuizRamdom:" + e.getMessage());
        }
        return data;
    }

}
