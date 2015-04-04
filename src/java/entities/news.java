/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Truong Van Dung
 */
public class news implements Cloneable {

    private int news_id;
    private String news_title;
    private String news_alias;
    private String news_author;
    private String news_url;
    private String news_img_sprite;
    private String news_file_vtt;
    private String news_source;
    private String news_date;
    private String news_infor;
    private String news_full;
    private String news_check;
    private String news_img;
    private int news_view;
    private String news_taglist;
    private String news_keyword;
    private int u_id;
    private String type_id;
    private String type_alias;
    private String cate_alias;
    private String username;

    public news() {
    }

    public news(int news_id, String news_title, String news_alias, String news_author, String news_source, String news_date, String news_infor, String news_full, String news_check, String news_img, int news_view, String news_taglist, String news_keyword, int u_id, String type_id, String url, String img_sprite, String file_vtt) {
        this.news_id = news_id;
        this.news_title = news_title;
        this.news_alias = news_alias;
        this.news_author = news_author;
        this.news_source = news_source;
        this.news_date = news_date;
        this.news_infor = news_infor;
        this.news_full = news_full;
        this.news_check = news_check;
        this.news_img = news_img;
        this.news_view = news_view;
        this.news_taglist = news_taglist;
        this.news_keyword = news_keyword;
        this.u_id = u_id;
        this.type_id = type_id;
        this.news_file_vtt = file_vtt;
        this.news_img_sprite = img_sprite;
        this.news_url = url;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_alias() {
        return news_alias;
    }

    public void setNews_alias(String news_alias) {
        this.news_alias = news_alias;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_infor() {
        return news_infor;
    }

    public void setNews_infor(String news_infor) {
        this.news_infor = news_infor;
    }

    public String getNews_full() {
        return news_full;
    }

    public void setNews_full(String news_full) {
        this.news_full = news_full;
    }

    public String getNews_check() {
        return news_check;
    }

    public void setNews_check(String news_check) {
        this.news_check = news_check;
    }

    public String getNews_img() {
        return news_img;
    }

    public void setNews_img(String news_img) {
        this.news_img = news_img;
    }

    public int getNews_view() {
        return news_view;
    }

    public void setNews_view(int news_view) {
        this.news_view = news_view;
    }

    public String getNews_taglist() {
        return news_taglist;
    }

    public void setNews_taglist(String news_taglist) {
        this.news_taglist = news_taglist;
    }

    public String getNews_keyword() {
        return news_keyword;
    }

    public void setNews_keyword(String news_keyword) {
        this.news_keyword = news_keyword;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getNews_source() {
        return news_source;
    }

    public void setNews_source(String news_source) {
        this.news_source = news_source;
    }

    public String getType_alias() {
        return type_alias;
    }

    public void setType_alias(String type_alias) {
        this.type_alias = type_alias;
    }

    public String getCate_alias() {
        return cate_alias;
    }

    public void setCate_alias(String cate_alias) {
        this.cate_alias = cate_alias;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public String getNews_img_sprite() {
        return news_img_sprite;
    }

    public void setNews_img_sprite(String news_img_sprite) {
        this.news_img_sprite = news_img_sprite;
    }

    public String getNews_file_vtt() {
        return news_file_vtt;
    }

    public void setNews_file_vtt(String news_file_vtt) {
        this.news_file_vtt = news_file_vtt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}
