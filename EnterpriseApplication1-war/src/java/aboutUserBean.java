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
@ManagedBean(name="aboutUserBean",eager=true)
public final class aboutUserBean implements Serializable {

    /**
     * Creates a new instance of aboutBean
     */
    public String user_id;
    public String userName;
    public String yas;
    public String boy;
    public String kilo;
    public String cinsiyet;
    public String meslekGrubu;
    public String diyetID;
    public int kalanGun;
    public String diyetisyenID;
    
    
    private static final long serialVersionUID = 1L;
    
    
    //HttpSession session;
    
          
    FacesContext context;
    HttpServletRequest request;
    HttpSession session;
    
    

    public aboutUserBean() {
        
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
        this.user_id = session.getAttribute("_userID").toString();
        
        pullFromDatabase();
    } 
    
    
    public void setUserName(String userName){
        this.userName=userName;
    }
    
    public void setYas(String yas){
        this.yas=yas;
    }
    
    public void setBoy(String boy){
        this.boy=boy;
    }
    
    public void setKilo(String kilo){
        this.kilo=kilo;
    }
    
    public void setCinsiyet(String cinsiyet){
        this.cinsiyet=cinsiyet;
    }
    
    public void setMeslekGrubu(String meslekGrubu){
        this.meslekGrubu=meslekGrubu;
    }
    
    public void setDiyetID(String diyetID){
        this.diyetID=diyetID;
    }
    
    public void setKalanGun(int kalanGun){
        this.kalanGun=kalanGun;
    }
    
    public void setDiyetisyenID(String diyetisyenID){
        this.diyetisyenID=diyetisyenID;
    }
    
    
    
    /////Getter
    
    
    public String getUserName(){
        return this.userName;
    }
    
    public String getYas(){
        return this.yas;
    }
    
    public String getBoy(){
        return this.boy;
    }
    
    public String getKilo(){
        return this.kilo;
    }
    
    public String getCinsiyet(){
        return this.cinsiyet;
    }
    
    public String getMeslekGrubu(){
        return this.meslekGrubu;
    }
    
    public String getDiyetID(){
        return this.diyetID;
    }
    
    public int getKalanGun(){
        return this.kalanGun;
    }
    
    public String getDiyetisyenID(){
        return this.diyetisyenID;
    }
    
    
    ////////////////////////////////
    
    
    
    public void pullFromDatabase(){
    
        String sql="SELECT * from User where ( userID = ? );";
        
        ResultSet rs=DBHelper.pullDetailsFromDatabase(sql, user_id);
        
        try {
           
            this.userName=rs.getString("userName");
            this.yas=rs.getString("yas");
            this.boy=rs.getString("boy");
            this.kilo=rs.getString("kilo");
            this.cinsiyet=rs.getString("cinsiyet");
            this.meslekGrubu=rs.getString("meslekGrubu");
            this.diyetID=rs.getString("diyetID");
            this.kalanGun=rs.getInt("kalanGun");
            this.diyetisyenID=rs.getString("diyetisyenID");
            
        } catch (SQLException ex) {
            Logger.getLogger(aboutUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String addDiyetSelect(){
        
        return "addDiyetFromDiyetisyen";
    }
    
   
    
    


    
}
