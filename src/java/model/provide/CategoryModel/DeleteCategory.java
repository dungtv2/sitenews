/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.provide.CategoryModel;

import entities.category;
import entities.role;
import java.sql.CallableStatement;
import java.sql.Connection;
import model.ConnectionManager;
import model.I.IDelete;

/**
 *
 * @author Truong Van Dung
 */
public class DeleteCategory implements IDelete {

    private ConnectionManager connectManager;
    private Connection con;

    public DeleteCategory() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int delete(Object ob) {
        int iCheck = -1;
        category cate = (category) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_deleteCate(?)}");
            cs.setInt(1, cate.getCate_id());
            iCheck = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return iCheck;
        }
    }
}
