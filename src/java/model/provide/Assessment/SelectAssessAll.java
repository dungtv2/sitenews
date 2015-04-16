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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectAssessAll implements ISelect<assessment> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectAssessAll() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<assessment> select(assessment t) {
        List<assessment> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<assessment>();
            CallableStatement cs = con.prepareCall("{call sp_selectAllAssess}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                assessment n = new assessment();
                n.setAs_id(rs.getInt(1));
                n.setParent_id(rs.getInt(2));
                n.setCreate_date(rs.getDate(3));
                n.setWrite_date(rs.getDate(4));
                n.setU_id(rs.getInt(5));
                n.setNews_id(rs.getInt(6));
                n.setAs_context(rs.getString(7));
                n.setLike(rs.getInt(8));
                list.add(n);
            }
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }
    public static void main(String[] args){
        SelectAssessAll s = new SelectAssessAll();
        System.out.print(s.select(null).size());
        
    }
}
