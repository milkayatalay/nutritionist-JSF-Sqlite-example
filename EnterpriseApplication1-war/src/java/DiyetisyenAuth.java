/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author milkayatalay
 */
@SessionScoped
@ManagedBean(name="diyetisyenAuth",eager=true)
public class DiyetisyenAuth {

    /**
     * Creates a new instance of DiyetisyenAuth
     */
    public DiyetisyenAuth() {
        
        
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //this.session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
               
    }
    
    
    
     
    
    private String user_diyetisyen_id;
    private String user_diyetisyen_pwd;
    private String tempID;
    private String tempPWD;
    private final HttpSession session;
    private HttpServletRequest request;
      
    
    
    //sessionMap.put("somekey", yourVariable);
    
    
    
    
    public String getUserDiyetisyenID(){
        return user_diyetisyen_id;
    }
    
    public void setUserDiyetisyenID(String userID){
        this.user_diyetisyen_id=userID;
    }
    
    public String getUserDiyetisyenPWD(){
        return user_diyetisyen_pwd;
    }
    
    public void setUserDiyetisyenPWD(String userPWD){
        this.user_diyetisyen_pwd=userPWD;
    }
    
    
    public String checkFromDB(){
       
        //String queryStr="SELECT * from User where ( userID = ? and userPWD = ? );";
        String queryStr="SELECT diyetisyenID,diyetisyenPWD from Diyetisyen where ( diyetisyenID = ? );";
        try{
                            
            //ResultSet rs = DBHelper.pullCredData(queryStr, this.user_id, this.user_pwd);
            
            ResultSet rs = DBHelper.pullCredData(queryStr, this.user_diyetisyen_id);
            if(rs.next()){
                
                        tempID=rs.getString(1);
                        tempPWD=rs.getString(2);
                
            }
            
            
        }catch(SQLException ex){
            System.out.print(ex);
        }  
        if((tempID == null ? user_diyetisyen_id == null : tempID.equals(user_diyetisyen_id))&&(tempPWD == null ? user_diyetisyen_pwd == null : tempPWD.equals(user_diyetisyen_pwd))){
            
            session.setAttribute("_userDiyetisyenID", user_diyetisyen_id);
            return "personal_diyetisyen";
        }
        return "index";
    }       
       
}
