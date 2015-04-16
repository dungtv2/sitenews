/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author truongdung
 */
public class assessment {
    private int as_id;
    private Date create_date;
    private Date write_date;
    private int parent_id;
    private int u_id;
    private int news_id;
    private String as_context;
    private int like;

    public assessment() {
    }

    public assessment(int as_id, Date create_date, Date write_date, int parent_id, int u_id, int news_id, String as_context, int like) {
        this.as_id = as_id;
        this.create_date = create_date;
        this.write_date = write_date;
        this.parent_id = parent_id;
        this.u_id = u_id;
        this.news_id = news_id;
        this.as_context = as_context;
        this.like = like;
    }

    public int getAs_id() {
        return as_id;
    }

    public void setAs_id(int as_id) {
        this.as_id = as_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getAs_context() {
        return as_context;
    }

    public void setAs_context(String as_context) {
        this.as_context = as_context;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
    
    
}
