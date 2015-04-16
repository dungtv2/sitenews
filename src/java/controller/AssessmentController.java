/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.assessment;
import entities.role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.provide.Assessment.DeleteAssess;
import model.provide.Assessment.InsertAssess;
import model.provide.Assessment.SelectAssessAll;
import model.provide.Assessment.UpdateAssess;
import model.provide.Delete;
import model.provide.Insert;
import model.provide.Select;
import model.provide.Update;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "assBean")
@RequestScoped
public class AssessmentController {

    private FacesContext facesContext;

    private Select<assessment> select;
    private Update update;
    private Delete delete;
    private Insert insert;

    private List<assessment> listAssess = new ArrayList<assessment>();
    private assessment ass;
    private boolean flag;

    {
        init();
    }

    private void selectAll() {
        listAssess = select.select(null);
        ass = new assessment();
    }

    private void init() {
        facesContext = FacesContext.getCurrentInstance();
        select = new Select<assessment>(new SelectAssessAll());
        listAssess = select.select(null);
        ass = new assessment();

    }

    public AssessmentController() {

    }

    public void insert() {
        String name = "";
        String alert = "";
        if (!flag) {
            insert = new Insert(new InsertAssess());
            if (insert.insert(ass) > 0) {
                name = "success";
                alert = "Insert success";
            } else {
                name = "failed";
                alert = "Insert failed";
            }
        } else {
            update = new Update(new UpdateAssess());
            if (update.update(ass) > 0) {
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

    public void delete(assessment ass) {
        delete = new Delete(new DeleteAssess());
        String name = "";
        String alert = "";
        if (delete.delete(ass) > 0) {
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

    public List<assessment> getListAssess() {
        return listAssess;
    }

    public assessment getAssess() {
        return ass;
    }

    public void setAss(assessment ass) {
        this.ass = ass;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
