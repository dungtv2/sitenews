/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.provide.Assessment;

import entities.assessment;
import java.sql.CallableStatement;
import java.sql.Connection;
import model.ConnectionManager;
import model.I.IDelete;

/**
 *
 * @author Truong Van Dung
 */
public class DeleteAssess implements IDelete {

    private ConnectionManager connectManager;
    private Connection con;

    public DeleteAssess() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public int delete(Object ob) {
        int iCheck = -1;
        assessment as = (assessment) ob;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            CallableStatement cs = con.prepareCall("{call sp_deleteAssess(?)}");
            cs.setInt(1, as.getAs_id());
            iCheck = cs.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return iCheck;
        }
    }
}
