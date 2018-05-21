/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milkayatalay
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC;


public class DBHelper {
    
    public static Connection _conn;
    //private static final String _connStr = "jdbc:sqlite:/home/milkayatalay/Desktop/mainDB";///değişecek
   
    
    public static Connection connect() {
        try {
            // SQLite connection string
            //String url = "jdbc:sqlite:C://sqlite/db/test.db";
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url ="jdbc:sqlite:/home/milkayatalay/Desktop/Çalışmalarım/ödevler/java ödev/mainDB";
        if(_conn!=null){
            try{
                _conn.close();
            }catch(SQLException ex){
                
            }
        }
        _conn = null;
        
        try {
            _conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return _conn;
    }
    
    
    
   
    public static void insert(String sql,String name, String pwd,String userName,String yas,String boy,String kilo,String cinsiyet,String meslekGrubu) {
        
        _conn=connect();
 
        try {
            PreparedStatement pstmt = _conn.prepareStatement(sql); 
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            pstmt.setString(3, userName);
            pstmt.setString(4, yas);
            pstmt.setString(5, boy);
            pstmt.setString(6, kilo);
            pstmt.setString(7, cinsiyet);
            pstmt.setString(8, meslekGrubu);

            
            
            pstmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
        public static void insertDiyet(String sql,String diyetID, String diyetName,String diyetAmac,String beslenme,int diyetTime) {
        
        _conn=connect();
 
        try {
            PreparedStatement pstmt = _conn.prepareStatement(sql); 
            pstmt.setString(1, diyetID);
            pstmt.setString(2, diyetName);
            pstmt.setString(3, diyetAmac);
            pstmt.setString(4, beslenme);
            pstmt.setInt(5, diyetTime);            
            
            pstmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    //public static ResultSet pullCredData(String sql, String userID,String userPWD) throws SQLException{
    public static ResultSet pullCredData(String sql, String userID) throws SQLException{           
            try{
            _conn=connect();
            }catch(Exception ex){
                return null;
            }
            System.out.println(sql);
            try{
            PreparedStatement pstmt  = _conn.prepareStatement(sql);
            
            pstmt.setString(1,userID);            
            //pstmt.setString(2,userPWD);
            ResultSet rs  = pstmt.executeQuery();
            
            return rs;
            }catch(SQLException ex){
                return null;
            }

            /*while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("capacity"));
            }*/
        
    }
    
    
    public static ResultSet pullDetailsFromDatabase(String sql,String userID){
        try{
            _conn=connect();
        }catch(Exception ex){
            return null;
        }
        
        try{
            PreparedStatement pstmtD =_conn.prepareStatement(sql);
            pstmtD.setString(1,userID);
            ResultSet rs=pstmtD.executeQuery();
            return rs;
        }catch(SQLException ex){
            return null;
        }
    }
    
    public static void setDiyetFromDiyetisyen(String sql,String diyetID,int kalanGun,String diyetisyenID,String userID){
        try{
            _conn=connect();
        }catch(Exception ex){
            
        }
        try{
        PreparedStatement pstmt = _conn.prepareStatement(sql); 
        pstmt.setString(1,diyetID);
        pstmt.setInt(2,kalanGun);
        pstmt.setString(3,diyetisyenID);        
        pstmt.setString(4,userID);      
        
        
        pstmt.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(addDietBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static ResultSet readDiyetCreds(String sql){
        
        try{
            _conn=connect();
        }catch(Exception ex){
            return null;
        }
        
        try{
      
            PreparedStatement pstmtD =_conn.prepareStatement(sql);
            
            ResultSet rs=pstmtD.executeQuery();
            return rs;
        
        }catch(SQLException ex){
            
            return null;
        }         
    }
    
    public static ResultSet readDiyetisyenCreds(String sql){
        
        try{
            _conn=connect();
        }catch(Exception ex){
            return null;
        }
        
        try{
      
            PreparedStatement pstmtD =_conn.prepareStatement(sql);
            ResultSet rs=pstmtD.executeQuery();
            
            return rs;
        
        }catch(SQLException ex){
            
            return null;
        }          
    }
    
}
