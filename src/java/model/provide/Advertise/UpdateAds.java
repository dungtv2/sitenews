/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.Advertise;

import model.provide.RoleModel.*;
import entities.role;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import model.ConnectionManager;
import model.I.IUpdate;

/**
 *
 * @author Truong Van Dung
 */
public class UpdateAds implements IUpdate {

    private ConnectionManager connectManager;
    private Connection con;

    public UpdateAds() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int update(Object ob) {
        int check = 0;
        role role = (role) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_updateRole(?,?,?,?,?)}");
            java.sql.Date write_date = new Date(new java.util.Date().getTime());
            cs.setInt(1, role.getRole_id());
            cs.setDate(2, write_date);
            //cs.setInt(3, role.getWrite_uid());
            cs.setString(4, role.getRole_name());
            cs.setString(5, role.getRole_des());

            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }

}
