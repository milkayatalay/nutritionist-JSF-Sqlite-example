
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
public class DiyetModel {

    public int diyetTime;
    public String beslenme;
    public String diyetAmac;
    public String diyetName;
    public String diyetID;
    
        private final HttpSession session;
        private HttpServletRequest request;
    
    public DiyetModel(){
                
        FacesContext context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest)context.getExternalContext().getRequest();
        session = request.getSession(false);
    }
    
    public void setDiyetID(String diyetID){
        this.diyetID=diyetID;
    }
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
    
    
    //getter
    
    
    
    public String getDiyetID(){
        return this.diyetID;
    }
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
    
    
    
    public void selectedDiyet(){
        
        session.setAttribute("_selectedDiyetID", this.diyetID);
        session.setAttribute("_selectedDiyetKalanGun", this.diyetTime);
        
        
    }
}
