/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.RoleModel;

import entities.role;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectRoleAll implements ISelect<role> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectRoleAll() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<role> select(role t) {
        List<role> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<role>();
            CallableStatement cs = con.prepareCall("{call sp_selectRoleAll}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                role n = new role();
                n.setRole_id(rs.getInt(1));
                n.setCreate_date(rs.getDate(2));
                n.setCreate_uid(rs.getInt(3));
                n.setWrite_date(rs.getDate(4));
                n.setWrite_uid(rs.getInt(5));
                n.setRole_name(rs.getString(6));
                n.setRole_des(rs.getString(7));
                list.add(n);
            }
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }
    public static void main(String[] args){
        SelectRoleAll s = new SelectRoleAll();
        System.out.print(s.select(null).size());
        
    }
}
