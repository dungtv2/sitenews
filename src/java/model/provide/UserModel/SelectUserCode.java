/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.UserModel;

import entities.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Truong Van Dung
 */
public class SelectUserCode {

    public static List<user> select(ResultSet rs) {
        List<user> list = null;
        try {
            list = new ArrayList<user>();
            while (rs.next()) {
                user n = new user();
                SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
                n.setId(rs.getInt(1));
                Date regis = new Date(rs.getDate(2).getTime());
                n.setCreate_date(sim.format(regis));
                n.setWrite_date(rs.getDate(3));
                n.setName(rs.getString(4));
                n.setUsername(rs.getString(5));
                n.setPassword(rs.getString(6));
                n.setImg(rs.getString(7));
                n.setEmail(rs.getString(8));
                if (rs.getDate(9) != null) {
                    Date birthday = new Date(rs.getDate(9).getTime());
                    n.setBirthday(sim.format(birthday));
                }
                n.setSex(rs.getString(10));
                n.setStatus(rs.getBoolean(11));
                n.setRoleid(rs.getInt(12));
                n.setRole_name(rs.getString(18));
                list.add(n);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
