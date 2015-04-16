/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.Assessment;

import entities.assessment;
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
public class UpdateAssess implements IUpdate {

    private ConnectionManager connectManager;
    private Connection con;

    public UpdateAssess() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int update(Object ob) {
        int check = 0;
        assessment as = (assessment) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_updateAssess(?,?,?)}");
            java.sql.Date write_date = new Date(new java.util.Date().getTime());
            cs.setInt(1, as.getAs_id());
            cs.setDate(2, write_date);
            cs.setString(3, as.getAs_context());

            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }

}
