
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milkayatalay
 */
public class DiyetisyenModel {
    
    public String diyetisyenID;
    public String diyetisyenName;
    public String diyetAbleArray;
    public String ilgiliUser;
    
     private final HttpSession session;
    private HttpServletRequest request;
    
    public DiyetisyenModel(){
        FacesContext context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
    }
    
    public void setDiyetisyenID(String diyetisyenID){
        this.diyetisyenID=diyetisyenID;
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
    
    
    //getter
    
    
    
    public String getDiyetisyenID(){
        return this.diyetisyenID;
    }
    public String getDiyetisyenName(){
        return this.diyetisyenName;
    }
    public String getDiyetAbleArray(){
        return this.diyetAbleArray;
    }
    
    public String getIlgiliUser( ){
        return this.ilgiliUser;
    }
    
    public void selectedDiyetisyen(){
        
        session.setAttribute("_selectedDiyetisyenID", this.diyetisyenID);
        
    }
    
    
}
