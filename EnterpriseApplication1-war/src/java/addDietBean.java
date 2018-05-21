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
@ManagedBean(name="addDietBean",eager=true)
public class addDietBean implements Serializable{
    
    FacesContext context;
    HttpServletRequest request;
    HttpSession session;
    private final String user_id;
    private int kalanGun;
    private String diyetID;
    private String diyetisyenID;
    
    private List<DiyetisyenModel> diyetisyenler;
    private List<DiyetModel> diyetler;
    
    
    

    /**
     * Creates a new instance of addDietBean
     */
    public addDietBean() {
               
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
        this.user_id = session.getAttribute("_userID").toString();
    }
   
    
    public void addDiet(){
        
        this.diyetisyenID = session.getAttribute("_selectedDiyetisyenID").toString();
        this.diyetID=session.getAttribute("_selectedDiyetID").toString();
        this.kalanGun=Integer.parseInt(session.getAttribute("_selectedDiyetKalanGun").toString());
        String queryStr="UPDATE User SET diyetID = ?, kalanGun = ?,diyetisyenID=? WHERE (userid = ?);";
        DBHelper.setDiyetFromDiyetisyen(queryStr, diyetID,kalanGun,diyetisyenID,user_id);        
        
    }
    
    
    

    @PostConstruct
    public void init(){
        
        try {
            String sql="SELECT * from Diyetisyen;";
            
            ResultSet rs=DBHelper.readDiyetisyenCreds(sql);
            diyetisyenler = new ArrayList<DiyetisyenModel>();
            while(rs.next()){
                DiyetisyenModel model=new DiyetisyenModel();
                
                model.setDiyetisyenID(rs.getString("diyetisyenID"));
                model.setDiyetisyenName(rs.getString("diyetisyenName"));
                model.setDiyetAbleArray(rs.getString("diyetAbleArray"));
                model.setIlgiliUser(rs.getString("ilgiliUser"));
            

                diyetisyenler.add(model);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(addDietBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public List<DiyetisyenModel> getDiyetisyenler() {
        return diyetisyenler;
    }
    
    public List<DiyetModel> getDiyetler() {
        return diyetler;
    }
    

    
    public void setDiyetisyenID(String diyetisyenID){
        this.diyetisyenID=diyetisyenID;
        
    }
    
        
    public void setDiyetID(String diyetID){
        this.diyetID=diyetID;
        
    }
           
}
