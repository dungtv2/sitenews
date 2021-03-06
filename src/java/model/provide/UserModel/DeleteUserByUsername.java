/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.provide.UserModel;

import entities.user;
import java.sql.CallableStatement;
import java.sql.Connection;
import model.ConnectionManager;
import model.I.IDelete;

/**
 *
 * @author Truong Van Dung
 */
public class DeleteUserByUsername implements IDelete {

    private ConnectionManager connectManager;
    private Connection con;

    public DeleteUserByUsername() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int delete(Object ob) {
        int iCheck = -1;
        user user = (user) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_deleteUserByUsername(?)}");
            cs.setString(1, user.getUsername());
            iCheck = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return iCheck;
        }
    }
}
