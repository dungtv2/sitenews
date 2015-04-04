/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.category;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.provide.CategoryModel.DeleteCategory;
import model.provide.CategoryModel.InsertCategory;
import model.provide.CategoryModel.SelectCategoryAll;
import model.provide.CategoryModel.UpdateCategory;
import model.provide.Delete;
import model.provide.Insert;
import model.provide.Select;
import model.provide.Update;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "cateAdbean")
@RequestScoped
public class CategoryAdminController {

    private FacesContext facesContext;

    private Select<category> select;
    private Update update;
    private Delete delete;
    private Insert insert;

    private List<category> listCategory = new ArrayList<category>();
    private category cate;
    private boolean flag;

    {
        init();
    }

    public CategoryAdminController() {

    }

    private void selectAll() {
        listCategory = select.select(null);
        cate = new category();
    }

    private void init() {
        facesContext = FacesContext.getCurrentInstance();
        select = new Select<category>(new SelectCategoryAll());
        listCategory = select.select(null);
        cate = new category();

    }

    public void action() {
        String name = "";
        String alert = "";
        if (!flag) {
            insert = new Insert(new InsertCategory());
            if (insert.insert(cate) > 0) {
                name = "success";
                alert = "Insert success";
            } else {
                name = "failed";
                alert = "Insert failed";
            }
        } else {
            update = new Update(new UpdateCategory());
            if (update.update(cate) > 0) {
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

    public void delete(category cate) {
        delete = new Delete(new DeleteCategory());
        String name = "";
        String alert = "";
        if (delete.delete(cate) > 0) {
            name = "success";
            alert = "Delete success";
        } else {
            name = "failed";
            alert = "Delete failed";
        }
        sendAttribute(name, alert);
        selectAll();
    }

    private void sendAttribute(String name, String value) {
        ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute(name, value);
    }

    private void message(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    public List<category> getListCategory() {
        return listCategory;
    }

    public category getCate() {
        return cate;
    }

    public void setCate(category cate) {
        this.cate = cate;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
