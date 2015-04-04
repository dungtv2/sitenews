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
import model.I.IUpdate;

/**
 *
 * @author Truong Van Dung
 */
public class UpdateCategory implements IUpdate {

    private ConnectionManager connectManager;
    private Connection con;

    public UpdateCategory() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int update(Object ob) {
        int check = 0;
        category cate = (category) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_updateCate(?,?,?,?,?,?,?)}");
            java.sql.Date write_date = new java.sql.Date(new java.util.Date().getTime());
            cs.setDate(1, write_date);
            cs.setInt(2, cate.getWrite_uid());
            cs.setInt(3, cate.getCate_id());
            cs.setInt(4, cate.getParent_id());
            cs.setString(5, cate.getCate_alias());
            cs.setString(6, cate.getCate_name());
            cs.setInt(7, cate.getCate_order());
            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }
}
