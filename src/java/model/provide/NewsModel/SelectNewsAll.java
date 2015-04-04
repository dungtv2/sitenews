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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import model.ConnectionManager;
import model.I.ISelect;

/**
 *
 * @author Truong Van Dung
 */
public class SelectNewsAll implements ISelect<news> {

    private ConnectionManager connectManager;
    private Connection con;

    public SelectNewsAll() {
        connectManager = ConnectionManager.newInstance();
    }

    @Override
    public List<news> select(news t) {
        List<news> list = null;
        connectManager.openConnection();
        con = connectManager.getConnection();
        try {
            list = new ArrayList<news>();
            CallableStatement cs = con.prepareCall("{call sp_selectNewsAll}");
            ResultSet rs = cs.executeQuery();
            list = SelectCode.select(rs);
        } catch (Exception e) {
        } finally {
            connectManager.closeConnection();
            return list;
        }
    }
}
