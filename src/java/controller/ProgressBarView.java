/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
public class ProgressBarView implements Serializable {

    private FacesContext facesContext;
    private long progress;
    private List<String> list = new ArrayList<String>();

    {
        facesContext = FacesContext.getCurrentInstance();
    }

    public void upload() {
        String alert = "";
        String name = "";
        try {
            List<Part> listPart = new ArrayList<Part>();
            facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            Collection<Part> part1 = request.getParts();
            for (Part pr : part1) {
                if (pr.getSubmittedFileName() != null && pr.getSubmittedFileName() != "") {
                    listPart.add(pr);
                }
            }
            List<String> list = new ArrayList<String>();
            InputStream is = null;
            FileOutputStream os = null;
            try {
                String path = selectpath();
                //lay ngay thang nam
                Calendar cl = Calendar.getInstance();
                int year = cl.get(Calendar.YEAR);
                int month = cl.get(Calendar.MONTH) + 1;
                int day = cl.get(Calendar.DATE);

                //kiem tra xem folder da ton tai hay chua
                String[] st = {"images", "video", "text"};
                String path_day_i = "";
                String path_day_v = "";
                String path_day_t = "";
                for (int i = 0; i < st.length; i++) {
                    String pr = st[i];
                    File file_folder = new File(path + "web\\upload\\" + pr);
                    File file_year = new File(path + "web\\upload\\" + pr + "\\" + year);
                    File file_month = new File(path + "web\\upload\\" + pr + "\\" + year + "\\" + month);
                    File file_day = null;
                    if (pr.equals("images")) {
                        path_day_i = path + "web\\upload\\" + pr + "\\" + year + "\\" + month + "\\" + day;
                        file_day = new File(path_day_i);
                    } else if (pr.equals("video")) {
                        path_day_v = path + "web\\upload\\" + pr + "\\" + year + "\\" + month + "\\" + day;
                        file_day = new File(path_day_v);
                    } else if (pr.equals("text")) {
                        path_day_t = path + "web\\upload\\" + pr + "\\" + year + "\\" + month + "\\" + day;
                        file_day = new File(path_day_t);
                    }
                    if (!file_folder.exists()) {
                        file_folder.mkdir();
                    }
                    if (!file_year.exists()) {
                        file_year.mkdir();
                    }
                    if (!file_month.exists()) {
                        file_month.mkdir();
                    }
                    if (!file_day.exists()) {
                        file_day.mkdir();
                    }
                }
                //duyet qua cac part
                String url = "";
                String file_name = "";
                long file_size = 0;
                String file_type = "";
                for (Part part : listPart) {
                    file_name = part.getSubmittedFileName();
                    file_size = part.getSize();
                    file_type = part.getContentType();
                    if (file_type.matches("^(image/[a-zA-Z0-9]+)")) {
                        url = path_day_i + "\\" + file_name;
                    } else if (file_type.matches("^(video/[a-zA-Z0-9]+)")) {
                        url = path_day_v + "\\" + file_name;
                    } else {
                        url = path_day_t + "\\" + file_name;
                    }
                    is = part.getInputStream();
                    int read = 0;
                    File file_write = new File(url);
                    if (file_write.exists()) {
                        int count = 0;
                        String type = file_type.substring(file_type.indexOf("/") + 1, file_type.length());
                        //neu file ton tai thi doi thanh ten file moi
                        while (file_write.exists()) {
                            String em = file_name.substring(0, file_name.length() - type.length());
                            url = url.substring(0, url.indexOf(em) + em.length()) + "_" + count + file_name.substring(file_name.length() - type.length());
                            file_write = new File(url);
                            count++;
                        }
                    }
                    os = new FileOutputStream(file_write);
                    while ((read = is.read()) != -1) {
                        os.write(read);
                        progress = (os.getChannel().size() * 100) / file_size;
                    }
                    list.add(url.substring(url.indexOf("upload")));
                }
                name = "success";
                alert = "Upload file Success!!!";
            } catch (Exception ex) {
                list = null;
                name = "failed";
                alert = "Upload file failed(Error:" + ex.getMessage() + ")!!!";
            } finally {
                if (is != null && os != null) {
                    try {
                        is.close();
                        os.close();
                    } catch (Exception ex) {
                        list = null;
                        message(ex.getMessage());
                    }
                }
                this.list = list;
            }
        } catch (Exception ex) {
            name = "failed";
            alert = "Upload file failed(Error:" + ex.getMessage() + ")!!!";
        }
        sendAttribute(name, alert);
    }

    private void sendAttribute(String name, String value) {
        ((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute(name, value);
    }

    public void message(String msg) {
        facesContext.addMessage(null, new FacesMessage(msg));
    }

    private String selectpath() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String he = request.getRealPath(request.getContextPath());
        String path = he.substring(0, he.indexOf("build"));
        return path;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public void onComplete() {
        message("Upload Success");
    }

    public void cancel() {
    }

    public List<String> getList() {
        return list;
    }

}
