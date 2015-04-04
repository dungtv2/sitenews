/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Truong Van Dung
 */
public class UploadFile {
    
    private FacesContext facesContext;

    {
        facesContext = FacesContext.getCurrentInstance();
    }

    public String uploadone(Part part, HttpServletRequest request) {
        InputStream is = null;
        FileOutputStream os = null;
        String file_name = "";
        try {
            String path = selectpath();
            //lấy ngày tháng năm
            Calendar cl = Calendar.getInstance();
            int year = cl.get(Calendar.YEAR);
            int month = cl.get(Calendar.MONTH) + 1;
            int day = cl.get(Calendar.DATE);

            //kiem tra xem folder da ton tai hay chua nếu chưa thì sẽ khởi tạo
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
            String url = "";
            file_name = part.getSubmittedFileName();
            String file_type = part.getContentType();
            //kiểm tra xem file là loại nào
            if (file_type.matches("^(image/[a-zA-Z0-9]+)")) {
                url = path_day_i + "\\" + file_name;
            } else if (file_type.matches("^(video/[a-zA-Z0-9]+)")) {
                url = path_day_v + "\\" + file_name;
            } else {
                url = path_day_t + "\\" + file_name;
            }
            
            //đọc và ghi file
            is = part.getInputStream();
            int read = 0;
            File file_write = new File(url);
            if (file_write.exists()) {
                int count = 0;
                String type = file_type.substring(file_type.indexOf("/") + 1, file_type.length());
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
            }
            //lấy đường dẫn
            file_name = url.substring(url.indexOf("upload"));

        } catch (Exception ex) {
            message(ex.getMessage());
            request.setAttribute("failed", ex.getMessage());
        } finally {
            if (is != null && os != null) {
                try {
                    is.close();
                    os.close();
                } catch (Exception ex) {
                    message(ex.getMessage());
                    request.setAttribute("failed", ex.getMessage());
                }
            }
            return file_name;
        }
    }

    public List<String> upload(List<Part> listPart) {
        List<String> list = new ArrayList<String>();
        InputStream is = null;
        FileOutputStream os = null;
        try {
            for (Part part : listPart) {
                String file_name = part.getSubmittedFileName();
                long file_size = part.getSize();
                String file_type = part.getContentType();
                String path = selectpath();
                String url = path + "web\\images\\" + file_name;
                is = part.getInputStream();
                int read = 0;
                File file_write = new File(url);
                if (file_write.exists()) {
                    int count = 0;
                    String type = file_type.substring(file_type.indexOf("/") + 1, file_type.length());
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
                }
                file_name = file_write.getName();
                list.add(file_name);
            }
        } catch (Exception ex) {
            list = null;
            message(ex.getMessage());
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
            return list;
        }
    }

    public void message(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    private String selectpath() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String he = request.getRealPath(request.getContextPath());
        String path = he.substring(0, he.indexOf("build"));
        return path;
    }
}
