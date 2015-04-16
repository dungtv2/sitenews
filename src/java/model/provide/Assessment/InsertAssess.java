/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.Assessment;

import entities.assessment;
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
public class InsertAssess implements IInsert {

    private ConnectionManager connectManager;
    private Connection con;

    public InsertAssess() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int insert(Object ob) {
        int check = 0;
        assessment as = (assessment) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_inserAssess(?,?,?,?,?)}");
            java.sql.Date register = new java.sql.Date(new java.util.Date().getTime());
            cs.setInt(1, as.getParent_id());
            cs.setDate(2, register);
            cs.setInt(3, as.getU_id());
            cs.setInt(4, as.getNews_id());
            cs.setString(4, as.getAs_context());
            check = cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return check;
        }
    }
}
