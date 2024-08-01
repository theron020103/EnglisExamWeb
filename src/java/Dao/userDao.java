/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DAL.DBContext;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ACER
 */
public class userDao extends DBContext {
    Connection cnn; ///kết nối DB
        PreparedStatement stm; //// thực hiện câu lệnh spl
        ResultSet rs; //lưu trữ và xử lý rồi lấy giá trị về
        
        public userDao(){
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

        public boolean checkUser(String account, String pass){
            try {
                String strSQL="SELECT * FROM Users WHERE account=? AND password=?       ";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, account);
                stm.setString(2, pass);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    return true;
                }
            } catch (Exception e) {
                System.out.println("checkUser:" + e.getMessage());
            }
            return false;
        }
        
        public boolean checkAccount(String acc){
            try {
                String strSQL="select * from Users where account=?";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, acc);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    return true;
                }
            } catch (Exception e) {
                System.out.println("checkAccount:" + e.getMessage());
            }
            return false;
        }
        
        public String getNameByAccount(String account){
            try {
                String strSQL="select * from Users where account=?";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, account);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    return rs.getString(2);
                }
            } catch (Exception e) {
                System.out.println("checkUser:" + e.getMessage());
            }
            return "";
        }
        
        public void insertUser(Users u){
            try {
                String strSQL="insert into Users(username, account, password) values(?,?,?)";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, u.getName());
                stm.setString(2, u.getAccount());
                stm.setString(3, u.getPass());
                stm.executeUpdate(); 

            } catch (Exception e) {
                System.out.println("checkUser:" + e.getMessage());
            }
        }
        
        public boolean checkName(String name){
            if (name == null || name.trim().isEmpty()) 
            return false;
            
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (!Character.isLetter(c)) {
                    return false;
                }
            }
            return true;
        }
        
        public boolean checkSignupEmpty(String name, String account, String pass, String pass2){
            if(name.isEmpty() || account.isEmpty() || pass.isEmpty() || pass2.isEmpty())
                return false;
            return true;
        }
        
        public String getIdByAccount(String acc){
            try {
                String strSQL="select * from Users where account=?";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, acc);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    return rs.getString(1);
                }
            } catch (Exception e) {
                System.out.println("checkUser:" + e.getMessage());
            }
            return "";
        }
        
    public boolean checkAmin(String acc){
        try {
                String strSQL="select * from Users where account=?";
                stm=cnn.prepareStatement(strSQL);
                stm.setString(1, acc);
                rs=stm.executeQuery();
                while (rs.next()) {                
                    if(rs.getString(5).equals("1"))
                        return true;
                }
            } catch (Exception e) {
                System.out.println("checkUser:" + e.getMessage());
            }
            return false;
        }
       
    
}
