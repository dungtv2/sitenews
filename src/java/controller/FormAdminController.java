/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "frmAdBean")
@RequestScoped
public class FormAdminController {

    private RequestContext requestContext;
    private FacesContext facesContext;
    private HttpServletRequest request;
    private HttpServletResponse reponse;
    private HttpSession session;

    private String a_style;

    {
        facesContext = FacesContext.getCurrentInstance();
    }

    public FormAdminController() {
    }

    public void logout() {
        requestContext = RequestContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        try {
            facesContext.getExternalContext().redirect("../admin/login.xhtml");
        } catch (IOException ex) {
            requestContext.execute("alert('" + ex.getMessage() + "')");
        }
    }

    public String getA_style() {
        return a_style;
    }

    public void setA_style(String a_style) {
        this.a_style = a_style;
    }

}
