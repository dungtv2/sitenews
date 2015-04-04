/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Truong Van Dung
 */
public class category {

    private int cate_id;
    private Date create_date;
    private int create_uid;
    private Date write_date;
    private int write_uid;
    private String cate_alias;
    private String cate_name;
    private int cate_order;
    private int parent_id;

    public category() {
    }

    public category(int cate_id,Date create_date, int create_uid, Date write_date, int write_uid, String cate_alias, String cate_name, int cate_order, int parent_id) {
        this.cate_id = cate_id;
        this.create_date = create_date;
        this.create_uid = create_uid;
        this.write_date = write_date;
        this.write_uid = write_uid;
        this.cate_alias = cate_alias;
        this.cate_name = cate_name;
        this.cate_order = cate_order;
        this.parent_id = parent_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getCreate_uid() {
        return create_uid;
    }

    public void setCreate_uid(int create_uid) {
        this.create_uid = create_uid;
    }

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public int getWrite_uid() {
        return write_uid;
    }

    public void setWrite_uid(int write_uid) {
        this.write_uid = write_uid;
    }
    
    public String getCate_alias() {
        return cate_alias;
    }

    public void setCate_alias(String cate_alias) {
        this.cate_alias = cate_alias;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCate_order() {
        return cate_order;
    }

    public void setCate_order(int cate_order) {
        this.cate_order = cate_order;
    }
    public int getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
