/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.UserModel;

import entities.user;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectUserByDate extends AbstractSearchByAll implements ISelect<user> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectUserByDate() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<user> select(user t) {
        List<user> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<user>();
            CallableStatement cs = con.prepareCall("{call sp_selectUserByDate(?)}");
            SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date date = new java.sql.Date(sim.parse(t.getCreate_date()).getTime());
            cs.setDate(1, date);
            ResultSet rs = cs.executeQuery();
            list = SelectUserCode.select(rs);
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }

    @Override
    public void search(String str) {
        user user = new user();
        user.setCreate_date(str);
        if (select(user) != null && select(user).size() > 0) {
            super.setList(select(user));
            super.setNextSearch(null);
        } else {
            super.change(str);
        }
    }
}
