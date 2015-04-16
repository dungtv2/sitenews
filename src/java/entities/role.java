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
    private Date write_date;
    private String role_name;
    private String role_des;

    public role() {
    }

    public role(int role_id, Date create_date, Date write_date, String role_name, String role_des) {
        this.create_date = create_date;
        this.write_date = write_date;
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

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
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
