/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DAL.DBContext;
import Model.rePrint;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class resultDao extends DBContext {
    Connection cnn; ///kết nối DB
        PreparedStatement stm; //// thực hiện câu lệnh spl
        ResultSet rs; //lưu trữ và xử lý rồi lấy giá trị về
        
        public resultDao(){
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
        
        public ArrayList<rePrint> getRePrintById(String id){
            ArrayList<rePrint> data = new ArrayList<>();
            quizDao q = new quizDao();
            questionDao qu = new questionDao();
            try {
                String strSQL = "select * from results where id_taker = ?";
                stm = cnn.prepareStatement(strSQL);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String code = rs.getString(2);
                    int correct = rs.getInt(3);
                    String date = rs.getString(4);
                    String time = rs.getString(5);
                    double point = rs.getDouble(6);
                    String name = "";
                    if(code.equals("ramdom"))
                        name = "ramdom";
                    else
                         name = q.getNamebyCode(code);
                    int count = qu.getNumOfQuesByCode(code);
                    rePrint r = new rePrint(date, time, code, name, count, correct, point);
                    data.add(r);
                }
            } catch (Exception e) {
                System.out.println("getQuizsStu:" + e.getMessage());
            }
            return data;
        }


}
