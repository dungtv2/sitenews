/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.user;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.I.ISelect;
import model.provide.*;
import model.provide.UserModel.*;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "userAdBean")
@RequestScoped
public class UserAdminController {

    private FacesContext facesContext;

    private Select<user> select;
    private Update update;
    private Delete delete;
    private Insert insert;
    private Pagging<user> pagging;

    private List<user> list = new ArrayList<user>();
    private String delist;
    private user user;
    private Part part;
    private boolean flag;

    private int number_page, begin, end;
    private static int row_of_page, page;

    {
        init();
    }

    public UserAdminController() {
        pagging();
        ok();
    }

    public void em(ActionEvent evt) {
        user u = (user) evt.getComponent().getAttributes().get("abc");
        message(u.getUsername() + "");
    }

    public void pagging() {
        pagging = new Pagging<user>(new PaggingUser());
        String page = param("search");
        if (page != null && page != "") {
            this.page = Integer.parseInt(page);
        } else {
            if (this.page == 0) {
                this.page = 1;
            }
        }
        if (row_of_page == 0) {//số dòng trong 1 page
            row_of_page = 2;
        }
        int size = select.select(null).size();//tổng số dòng
        number_page = size / row_of_page;
        if (size % row_of_page > 0) {
            number_page++;
        }

        if (number_page > 3) {
            begin = 1;
            end = 3;
        } else {
            begin = 1;
            end = number_page;
        }
        if (this.page >= end) {
            if (this.page == number_page) {
                if (number_page > 3) {
                    begin = this.page - 2;
                    end = this.page;
                } else {
                    begin = 1;
                    end = this.page;
                }
            } else {
                begin = this.page - 1;
                end = this.page + 1;
            }
        }
        list = pagging.pagging(this.page, row_of_page);
    }

    //create instance when file run
    private void init() {
        facesContext = FacesContext.getCurrentInstance();
        select = new Select<user>(new SelectUserAll());
        user = new user();
    }

    private void selectAll() {
        list = select.select(null);
        user = new user();
    }

    //insert and upadate
    public void action() {
        String name = "";
        String alert = "";
        if (!flag) {
            insert = new Insert(new InsertUser());
            String img = upload();
            if (img != null && img != "") {
                user.setImg(img);
            } else {
                user.setImg(null);
            }
            if (insert.insert(user) > 0) {
                alert = "Insert success";
                name = "success";
            } else {
                alert = "Insert failed";
                name = "failed";
            }
        } else {
            update = new Update(new UpdateUser());
            if (update.update(user) > 0) {
                alert = "Update success";
                name = "success";
            } else {
                alert = "Update failed";
                name = "failed";
            }
            flag = false;
        }
        sendAttribute(name, alert);
        list = pagging.pagging(this.page, row_of_page);
        user = new user();

    }

    //delete user
    public void deleteAll() {
        String name = "";
        String alert = "";
        try {
            delete = new Delete(new DeleteUserById());
            String[] listde = delist.split(",");
            for (String str : listde) {
                user.setId(Integer.parseInt(str));
                int check = delete.delete(user);
            }
            alert = "Delete success";
            name = "success";
        } catch (Exception ex) {
            alert = ex.getMessage();
            name = "failed";
        } finally {
            delist = null;
            selectAll();
        }
    }

    public void delete(user user) {
        String name = "";
        String alert = "";
        delete = new Delete(new DeleteUserById());
        if (delete.delete(user) > 0) {
            alert = "Delete success";
            name = "success";
        } else {
            alert = "Delete failed";
            name = "failed";
        }
        sendAttribute(name, alert);
        selectAll();
    }

    //search
    private void ok() {
        String id = param("id_search");
        if (id != null && id != "") {
            String val = param("val_search");
            switch (id) {
                case "user_id":
                    user.setId(Integer.parseInt(val));
                    searchByUserId();
                    break;
                case "user_role":
                    user.setRoleid(Integer.parseInt(val));
                    searchByRole();
                    break;
                case "user_date":
                    user.setCreate_date(val);
                    searchByDate();
                    break;
                case "user_sex":
                    user.setSex(val);
                    searchBySex();
                    break;
                case "user_username":
                    user.setUsername(val);
                    searchByUsername();
                    break;
                case "search_all":
                    ChainSearchUser chain = new ChainSearchUser();
                    AbstractSearchByAll ab = chain.chain();
                    ab.search(val);
                    list = ab.getList();
                    break;
            }
        }
    }

    private void searchByUserId() {
        search(new SelectUserById(), user);
    }

    private void searchByDate() {
        search(new SelectUserByDate(), user);
    }

    private void searchByRole() {
        search(new SelectUserByRole(), user);
    }

    private void searchBySex() {
        search(new SelectUserBySex(), user);
    }

    private void searchByUsername() {
        search(new SelectUserByUsername(), user);
    }

    private void search(ISelect search, user user) {
        select.setSelect(search);
        list = select.select(user);
    }

    //upload file
    private String upload() {
        if (part.getSubmittedFileName() != null) {
            UploadFile up = new UploadFile();
            return up.uploadone(part, (HttpServletRequest) facesContext.getExternalContext().getRequest());
        } else {
            return null;
        }
    }

    //show message
    private void message(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    private void sendAttribute(String name, String value) {
        ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute(name, value);
    }

    //get param
    private String param(String name) {
        return facesContext.getExternalContext().getRequestParameterMap().get(name);
    }

    //getters and setters
    public List<user> getList() {
        return list;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getNumber_page() {
        return number_page;
    }

    public String getDelist() {
        return delist;
    }

    public void setDelist(String delist) {
        this.delist = delist;
    }

    public int getRow_of_page() {
        return row_of_page;
    }

    public void setRow_of_page(int row_of_page) {
        UserAdminController.row_of_page = row_of_page;
    }

    public int getPage() {
        return page;
    }

}
