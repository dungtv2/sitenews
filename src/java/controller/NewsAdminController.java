/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.news;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.I.ISelect;
import model.provide.*;
import model.provide.NewsModel.*;
import model.provide.UserModel.SelectUserAll;

/**
 *
 * @author Truong Van Dung
 */
@ManagedBean(name = "newsadBean")
@RequestScoped
public class NewsAdminController {

    private FacesContext facesContext;

    //Action
    private Insert insert;
    private Update update;
    private Delete delete;
    private Pagging<news> pagging;
    private Select<news> select;
    private List<news> list = new ArrayList<news>();

    private news news, news_for_search;

    private boolean flag;
    private String delist;

    private int number_page, begin, end;
    private static int row_of_page, page;

    {
        instanceInit();
    }

    public NewsAdminController() {
        pagging();
        ok();
    }

    //function insert or update news
    public void insert() {
        String name = "";
        String alert = "";
        if (flag) {
            update = new Update(new UpdateNews());
            int check = update.update(news);
            if (check > 0) {
                alert = "Update Success";
                name = "success";
                flag = false;
            } else {
                alert = "Update Failed";
                name = "failed";
            }
        } else if (!flag) {
            insert = new Insert(new InsertNews());
            int check = insert.insert(news);
            if (check > 0) {
                alert = "Insert Success";
                name = "success";
            } else {
                name = "failed";
                alert = "Insert Failed";
            }
        }
        sendAttribute(name, alert);
        init();
        news = new news();
    }

    //function delete news by id
    public void delete(news n) {
        delete = new Delete(new DeleteNews());
        String name = "";
        String alert = "";
        int check = delete.delete(n);
        if (check > 0) {
            if (check > 0) {
                alert = "Delete Success";
                name = "success";
                init();
            } else {
                alert = "Delete Failed";
                name = "failed";
            }
        }
        sendAttribute(name, alert);
    }

    public void deletelist() {
        delete = new Delete(new DeleteNews());
        String[] listde = delist.split(",");
        for (String str : listde) {
            news.setNews_id(Integer.parseInt(str));
            int check = delete.delete(news);
        }
        delist = null;
        init();
    }

    //function used chung
    private void instanceInit() {
        facesContext = FacesContext.getCurrentInstance();
        select = new Select<news>(new SelectNewsAll());
        news = new news();
    }

    private void init() {
        list = pagging.pagging(this.page, row_of_page);
    }

    public void pagging() {
        pagging = new Pagging<news>(new PaggingNews());
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

    //tìm kiếm
    private void ok() {
        String name = "";
        String alert = "";
        String id = param("id_search");
        if (id != null && id != "") {
            String val = param("val_search");
            try {
                news_for_search = (news) news.clone();
            } catch (CloneNotSupportedException ex) {
                alert = ex.getMessage();
                name = "failed";
            }
            switch (id) {
                case "news_id":
                    news_for_search.setNews_id(Integer.parseInt(val));
                    searchByNewsId();
                    break;
                case "news_title":
                    news_for_search.setNews_title(val);
                    searchByTitle();
                    break;
                case "datepicker":
                    news_for_search.setNews_date(val);
                    searchByDate();
                    break;
                case "news_view":
                    news_for_search.setNews_view(Integer.parseInt(val));
                    searchByView();
                    break;
                case "news_taglist":
                    news_for_search.setNews_taglist(val);
                    searchByTag();
                    break;
                case "news_keyword":
                    news_for_search.setNews_keyword(val);
                    searchBykey();
                    break;
                case "u_id":
                    searchByUser();
                    news_for_search.setUsername(val);
                    break;
                case "type_id":
                    news_for_search.setType_id(val);
                    searchByTypeId();
                    break;
                case "search_all":
                    ChainSearchNews chain = new ChainSearchNews();
                    AbstractSearchByAll ab = chain.chain();
                    ab.search(val);
                    list = ab.getList();
                    break;
            }
            name = "success";
            alert = "tìm kiếm thành công";
        }
        sendAttribute(name, alert);
    }

    private void searchByNewsId() {
        search(new SelectNewsByNewsID(), news_for_search);
    }

    private void searchByTitle() {
        search(new SelectNewsByTitle(), news_for_search);
    }

    private void searchByView() {
        search(new SelectNewsByView(), news_for_search);
    }

    private void searchByUser() {
        search(new SelectNewsByUser(), news_for_search);
    }

    private void searchByTypeId() {
        search(new SelectNewsByTypeId(), news_for_search);
    }

    private void searchByTag() {
        search(new SelectNewsByTag(), news_for_search);
    }

    private void searchBykey() {
        search(new SelectNewsByKey(), news_for_search);
    }

    private void searchByDate() {
        search(new SelectNewsByDate(), news_for_search);
    }

    private void search(ISelect search, news news) {
        select.setSelect(search);
        list = select.select(news);
    }

    private void message(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    private String param(String name) {
        return facesContext.getExternalContext().getRequestParameterMap().get(name);
    }

    private void sendAttribute(String name, String value) {
        ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute(name, value);
    }

    //getters and setters
    public news getNews() {
        return news;
    }

    public void setNews(news news) {
        this.news = news;
    }

    public List<news> getList() {
        return list;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDelist() {
        return delist;
    }

    public void setDelist(String delist) {
        this.delist = delist;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getPage() {
        return page;
    }

    public int getRow_of_page() {
        return row_of_page;
    }

    public void setRow_of_page(int row_of_page) {
        NewsAdminController.row_of_page = row_of_page;
    }

    public int getNumber_page() {
        return number_page;
    }

}
