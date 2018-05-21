

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milkayatalay
 */
@SessionScoped
@ManagedBean(name="authBean",eager=true)
public class Auth {
    
    
    private String user_id;
    private String user_pwd;
    private String tempID;
    private String tempPWD;
    private final HttpSession session;
    private HttpServletRequest request;
      
    public Auth(){
        

        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //this.session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
               
        
    }
    //
    
    //sessionMap.put("somekey", yourVariable);
    
    
    
    
    public String getUserID(){
        return user_id;
    }
    
    public void setUserID(String userID){
        this.user_id=userID;
    }
    
    public String getUserPWD(){
        return user_pwd;
    }
    
    public void setUserPWD(String userPWD){
        this.user_pwd=userPWD;
    }
    
    
    public String checkFromDB(){
       
        //String queryStr="SELECT * from User where ( userID = ? and userPWD = ? );";
        String queryStr="SELECT userID,userPWD from User where ( userID = ? );";
        try{
                            
            //ResultSet rs = DBHelper.pullCredData(queryStr, this.user_id, this.user_pwd);
            
            ResultSet rs = DBHelper.pullCredData(queryStr, this.user_id);
            if(rs.next()){
                
                        tempID=rs.getString(1);
                        tempPWD=rs.getString(2);
                
            }
            
            
        }catch(SQLException ex){
            System.out.print(ex);
        }  
        if((tempID == null ? user_id == null : tempID.equals(user_id))&&(tempPWD == null ? user_pwd == null : tempPWD.equals(user_pwd))){
            
            session.setAttribute("_userID", user_id);
            return "personal";
        }
        return "index";
    }       
       
}
