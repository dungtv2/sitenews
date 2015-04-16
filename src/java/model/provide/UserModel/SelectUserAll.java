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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectUserAll implements ISelect<user> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectUserAll() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<user> select(user t) {
        List<user> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<user>();
            CallableStatement cs = con.prepareCall("{call sp_selectUserAll}");
            ResultSet rs = cs.executeQuery();
            list = SelectUserCode.select(rs);
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }
}
