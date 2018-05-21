/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author milkayatalay
 */
@SessionScoped
@ManagedBean(name="aboutDiyetisyenBean",eager=true)
public final class aboutDiyetisyenBean implements Serializable {

    /**
     * Creates a new instance of aboutBean
     */
    
    public String diyetisyenName;

    public String diyetisyenID;
    
    public String diyetAbleArray;
    
    public String ilgiliUser; 
    
    
    private static final long serialVersionUID = 1L;
    
    
    //HttpSession session;
    
          
    FacesContext context;
    HttpServletRequest request;
    HttpSession session;
    
    

    public aboutDiyetisyenBean() {
        
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
        this.diyetisyenID=session.getAttribute("_userDiyetisyenID").toString();
               
        pullFromDatabase();
    } 
    
    
    
    public void setDiyetisyenName(String diyetisyenName){
        this.diyetisyenName=diyetisyenName;
    }
    
    public void setDiyetAbleArray(String diyetAbleArray){
        this.diyetAbleArray=diyetAbleArray;
    }

    public void setIlgiliUser(String ilgiliUser){
        this.ilgiliUser=ilgiliUser;
    }

    public void setDiyetisyenID(String diyetisyenID){
        this.diyetisyenID=diyetisyenID;
    }
    
    
    
    /////Getter
    

    
    public String getDiyetisyenName(){
        return this.diyetisyenName;
    }
    
    public String getDiyetAbleArray(){
        return this.diyetAbleArray;
    }
    
    public String getIlgiliUser(){
        return this.ilgiliUser;
    }
    
    public String getDiyetisyenID(){
        return this.diyetisyenID;
    }
    
    
    ////////////////////////////////
    
    
    
    public void pullFromDatabase(){
    
        String sql="SELECT * from Diyetisyen where ( diyetisyenID = ? );";
        
        ResultSet rs=DBHelper.pullDetailsFromDatabase(sql, diyetisyenID);
        
        try {
            this.ilgiliUser=rs.getString("ilgiliUser");
            this.diyetAbleArray=rs.getString("diyetAbleArray");
            this.diyetisyenName=rs.getString("diyetisyenName");
            this.diyetisyenID=rs.getString("diyetisyenID");
            
        } catch (SQLException ex) {
            Logger.getLogger(aboutUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String addDiyetRoute(){
        
        return "addDiyetWithDiyetisyen";
    }
    
   
    
    


    
}
