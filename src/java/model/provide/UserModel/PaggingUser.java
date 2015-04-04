/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.UserModel;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ConnectionManager;
import model.I.IPagging;
import model.provide.Pagging;

/**
 *
 * @author Truong Van Dung
 */
public class PaggingUser implements IPagging<user> {

    private ConnectionManager connectManager;
    private Connection con;

    {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<user> pagging(int page, int pageSize) {
        List<user> listUser = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            listUser = new ArrayList<user>();
            if (page == 1) {
                ps = con.prepareStatement("select top " + pageSize + " * from [user] inner join [role] on [user].role_id = [role].role_id order by [user].u_id");
                rs = ps.executeQuery();
            } else {
                int rowUsed = (page - 1) * pageSize;
                ps = con.prepareStatement("select top " + pageSize + " * from [user] inner join [role] on [user].role_id = [role].role_id where u_id not in(select top " + rowUsed + " u_id from [user] inner join [role] on [user].role_id = [role].role_id order by u_id)");
                rs = ps.executeQuery();
            }
            listUser = SelectUserCode.select(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectManager.closeConnection();
            return listUser;
        }
    }
}
