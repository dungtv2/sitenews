/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.provide.RoleModel;

import entities.role;
import entities.user;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ConnectionManager;
import model.I.IInsert;

/**
 *
 * @author Truong Van Dung
 */
public class InsertRole implements IInsert{

    private ConnectionManager connectManager;
    private Connection con;

    public InsertRole() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int insert(Object ob) {
        int check = 0;
        role role = (role) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_inserRole(?,?,?)}");
            java.sql.Date register = new java.sql.Date(new java.util.Date().getTime());
            cs.setDate(1, register);
            //cs.setInt(2, role.getWrite_uid());
            cs.setString(2, role.getRole_name());
            cs.setString(3, role.getRole_des());

            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }
    public static void main(String[] args){
        InsertRole i = new InsertRole();
        role r = new role();
        r.setRole_name("Administrator32");
        r.setRole_des("Người Có quyền to nhất cả thế giỡi luông í");
        
        i.insert(r);
    }
}
