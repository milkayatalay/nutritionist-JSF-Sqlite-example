/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.Context;
import javax.faces.bean.ApplicationScoped;
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
@ManagedBean(name="signInBean",eager=true)
public class SignInBean {
    
    private String newUserId;
    private String newUserPwd;
    
    private final HttpSession session;
    private HttpServletRequest request;
    private String userName;
    private String yas;
    private String boy;
    private String kilo;
    private String cinsiyet;
    private String meslekGrubu;
    private String diyetID;
    private int kalanGun;
    
    public SignInBean(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
    }
    
      
    

    public void setNewUserId(String newUserId){
        this.newUserId=newUserId;
    }
    
    public void setNewUserPwd(String newUserPwd){
        this.newUserPwd=newUserPwd;        
    }
    
    
    public String getNewUserId(){
        return newUserId;
    }
    
    public String getNewUserPwd(){
        return newUserPwd;
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
    
    
    ////////////////////////////////
    
    /**
     * Creates a new instance of SignInBean
     * @return 
     */

    public String setSignInToDatabase(){
        
        //DBHelper.connection();
        
        String queryStr="INSERT INTO User (userID, userPWD,userName,yas,boy,kilo,cinsiyet,meslekGrubu)\n"+"VALUES (?,?,?,?,?,?,?,?);";

        try{
            
            DBHelper.insert(queryStr,this.newUserId,this.newUserPwd,this.userName,this.yas,this.boy,this.kilo,this.cinsiyet,this.meslekGrubu);
            
            session.setAttribute("_userID", this.newUserId);
            
            return "personal";
            
        }catch(Exception ex){
            
            System.out.print(ex);
            return "index";
        }        
    }
    
}
