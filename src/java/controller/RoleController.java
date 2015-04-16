/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.role;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.provide.Delete;
import model.provide.Insert;
import model.provide.RoleModel.DeleteRole;
import model.provide.RoleModel.InsertRole;
import model.provide.RoleModel.SelectRoleAll;
import model.provide.RoleModel.UpdateRole;
import model.provide.Select;
import model.provide.Update;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "roleBean")
@RequestScoped
public class RoleController {

    private FacesContext facesContext;

    private Select<role> select;
    private Update update;
    private Delete delete;
    private Insert insert;

    private List<role> listRole = new ArrayList<role>();
    private role role;
    private boolean flag;

    {
        init();
    }

    private void selectAll() {
        listRole = select.select(null);
        role = new role();
    }

    private void init() {
        facesContext = FacesContext.getCurrentInstance();
        select = new Select<role>(new SelectRoleAll());
        listRole = select.select(null);
        role = new role();
        try {
            ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).setCharacterEncoding("UTF-8");
    }

    public RoleController() {

    }

    public void insert() {
        String name = "";
        String alert = "";
        if (!flag) {
            insert = new Insert(new InsertRole());
            if (insert.insert(role) > 0) {
                name = "success";
                alert = role.getRole_des() + ":" + ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCharacterEncoding() + " : Insert success";
            } else {
                name = "failed";
                alert = "Insert failed";
            }
        } else {
            update = new Update(new UpdateRole());
            if (update.update(role) > 0) {
                name = "success";
                alert = "Update success";
            } else {
                name = "failed";
                alert = "Update failed";
            }
            flag = false;
        }
        sendAttribute(name, alert);
        selectAll();
    }

    public void delete(role role) {
        delete = new Delete(new DeleteRole());
        String name = "";
        String alert = "";
        if (delete.delete(role) > 0) {
            name = "success";
            alert = "Delete success";
        } else {
            name = "failed";
            alert = "Delete failed";
        }
        sendAttribute(name, alert);
        selectAll();
    }

    private void message(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    private void sendAttribute(String name, String value) {
        ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute(name, value);
    }

    public List<role> getListRole() {
        return listRole;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
