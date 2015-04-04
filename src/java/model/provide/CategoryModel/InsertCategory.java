/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.CategoryModel;

import entities.category;
import java.sql.CallableStatement;
import java.sql.Connection;
import model.ConnectionManager;
import model.I.IInsert;

/**
 *
 * @author Truong Van Dung
 */
public class InsertCategory implements IInsert {

    private ConnectionManager connectManager;
    private Connection con;

    public InsertCategory() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int insert(Object ob) {
        int check = 0;
        category cate = (category) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_insertCate(?,?,?,?,?,?)}");
            java.sql.Date register = new java.sql.Date(new java.util.Date().getTime());
            cs.setDate(1, register);
            cs.setInt(2, cate.getCreate_uid());
            cs.setString(4, cate.getCate_alias());
            cs.setString(5, cate.getCate_name());
            cs.setInt(6, cate.getCate_order());
            cs.setInt(3, cate.getParent_id());
            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }
}
