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
public class role {

    private int role_id;
    private Date create_date;
    private int create_uid;
    private Date write_date;
    private int write_uid;
    private String role_name;
    private String role_des;

    public role() {
    }

    public role(int role_id, Date create_date, int create_uid, Date write_date, int write_uid, String role_name, String role_des) {
        this.create_date = create_date;
        this.create_uid = create_uid;
        this.write_date = write_date;
        this.write_uid = write_uid;
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_des = role_des;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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
    
    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_des() {
        return role_des;
    }

    public void setRole_des(String role_des) {
        this.role_des = role_des;
    }
}
