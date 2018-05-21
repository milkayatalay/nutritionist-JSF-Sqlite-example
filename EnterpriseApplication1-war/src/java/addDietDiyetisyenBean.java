/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
@ManagedBean(name="addDietDiyetisyenBean",eager=true)
public class addDietDiyetisyenBean implements Serializable{
    
    FacesContext context;
    HttpServletRequest request;
    HttpSession session;
    
    
    private String diyetID;
    private String diyetisyenID;
    
    private List<DiyetModel> diyetler;
    private String diyetName;
    private String diyetAmac;
    private String beslenme;
    private int diyetTime;
    
    
    public String getDiyetName(){
        return this.diyetName;
    }
    
    public String getDiyetAmac(){
        return this.diyetAmac;
    }
    
    public String getBeslenme(){
        return this.beslenme;
    }
    
    public int getDiyetTime(){
        return this.diyetTime;
    }
    
    public String getDiyetID(){
        return this.diyetID;
    }
    
    ////setter
    
    
    public void setDiyetName(String diyetName){
        this.diyetName=diyetName;
    }
    
    public void setDiyetAmac(String diyetAmac){
       this.diyetAmac=diyetAmac;
    }
    
    public void setBeslenme(String beslenme){
        this.beslenme=beslenme;
    }
    
    public void setDiyetTime(int diyetTime){
        this.diyetTime=diyetTime;
    }
    
    public void setDiyetID(String diyetID){
        this.diyetID=diyetID;
    }
    
    
    

    /**
     * Creates a new instance of addDietBean
     */
    public addDietDiyetisyenBean() {
               
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
        this.diyetisyenID = session.getAttribute("_userDiyetisyenID").toString();
    }
   
    
    public void addDiet(){
        
        
        
        String queryStr="INSERT INTO Diyet (diyetID, diyetName, diyetAmac,beslenme,diyetTime)\n"+"VALUES (?,?,?,?,?);";
        DBHelper.insertDiyet(queryStr, diyetID, diyetName, diyetAmac, beslenme, diyetTime);
        
    }
    
    
    

    @PostConstruct
    public void init(){
        

        
        try {
            String sql1="SELECT * from Diyet;";
            
            ResultSet rs1=DBHelper.readDiyetCreds(sql1);
            diyetler = new ArrayList<DiyetModel>();
            while(rs1.next()){
                DiyetModel model1=new DiyetModel();
                
                model1.setDiyetID(rs1.getString("diyetID"));
                model1.setDiyetName(rs1.getString("diyetName"));
                model1.setDiyetAmac(rs1.getString("diyetAmac"));
                model1.setBeslenme(rs1.getString("beslenme"));  
                model1.setDiyetTime(rs1.getInt("diyetTime"));

                diyetler.add(model1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(addDietBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
    public List<DiyetModel> getDiyetler() {
        return diyetler;
    }
    

    
    public void setDiyetisyenID(String diyetisyenID){
        this.diyetisyenID=diyetisyenID;
        
    }
    
           
}
