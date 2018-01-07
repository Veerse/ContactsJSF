package Control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name="control")
@SessionScoped

public class ControlAcces implements Serializable{

    private static final long serialVersionUID = 455659950717243336L;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String checkAcces(){

        FacesContext context = FacesContext.getCurrentInstance();

        if(!login.equals("root")) context.addMessage(null, new FacesMessage("Wrong login"));
        if(!password.equals("root")) context.addMessage(null, new FacesMessage("Wrong password"));

        if(context.getMessageList().size()>0) return(null);
        else return("missing");

    }

}
