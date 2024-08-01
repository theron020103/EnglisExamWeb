
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DAL.DBContext;
import Model.Question;
import Model.Quizs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class quizDao extends DBContext{
    Connection cnn; ///kết nối DB
        PreparedStatement stm; //// thực hiện câu lệnh spl
        ResultSet rs; //lưu trữ và xử lý rồi lấy giá trị về
        userDao d = new userDao();
        
        public quizDao(){
            connectDB();
        }
        
        private void connectDB(){
            cnn=connection; 
            if(cnn!=null){
                System.out.println("Connect Sucess!");
            } else {
                System.out.println("Connect fail!");
            }

        }
        public ArrayList<Quizs> getQuizsStu(String acc){
            ArrayList<Quizs> data = new ArrayList<>();
            String id = d.getIdByAccount(acc);
            try {
                String strSQL = "select * from quizs";
                stm = cnn.prepareStatement(strSQL);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString(1);
                    String name = rs.getString(2);
                    int pubValue = rs.getInt(3);
                    String id_creater = rs.getString(4);
                    boolean pub = (pubValue == 1);
                    if(pub==true || id.equals(id_creater)){
                        Quizs p = new Quizs(code, name, pub);
                        data.add(p);
                    }
                }
            } catch (Exception e) {
                System.out.println("getQuizsStu:" + e.getMessage());
            }
            return data;
        }
        
        public String getNamebyCode(String id){
            try {
                String strSQL="select * from quizs where code=?";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, id);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    return rs.getString(2);
                }
            } catch (Exception e) {
                System.out.println("getNamebyCode:" + e.getMessage());
            }
            return "";
        }
        
        public void insertQuiz(String code, String name, ArrayList<Question> q, String id){
            try {
                String strSQL="insert into quizs(code, name, pub, id_teacher) values(?,?,?,?)";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, code);
                stm.setString(2, name);
                stm.setInt(3, 0);
                stm.setString(4, id);
                stm.executeUpdate(); 
                questionDao d = new questionDao();
                for(Question a: q){
                    d.insertQuestion(a);
                }

            } catch (Exception e) {
                System.out.println("insertQuiz:" + e.getMessage());
            }
        }
    
}
