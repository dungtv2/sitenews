/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.NewsModel;

import entities.news;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionManager;

/**
 *
 * @author Truong Van Dung
 */
public class SelectCode {

    public static List<news> select(ResultSet rs) {
        List<news> list = null;
        try {
            list = new ArrayList<news>();
            while (rs.next()) {
                news n = new news();
                n.setNews_id(rs.getInt(1));
                n.setNews_title(rs.getString(2));
                n.setNews_alias(rs.getString(3));
                n.setNews_url(rs.getString(4));
                n.setNews_img_sprite(rs.getString(5));
                n.setNews_file_vtt(rs.getString(6));
                n.setNews_author(rs.getString(7));
                n.setNews_source(rs.getString(8));
                n.setNews_date(rs.getString(9));
                n.setNews_infor(rs.getString(10));
                n.setNews_full(rs.getString(11));
                n.setNews_check(rs.getString(12));
                n.setNews_img(rs.getString(13));
                n.setNews_view(rs.getInt(14));
                n.setNews_taglist(rs.getString(15));
                n.setNews_keyword(rs.getString(16));
                n.setU_id(rs.getInt(17));
                n.setUsername(rs.getString(30));
                n.setType_id(rs.getString(18));
                list.add(n);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
