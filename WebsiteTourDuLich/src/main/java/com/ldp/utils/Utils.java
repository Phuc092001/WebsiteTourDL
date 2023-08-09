/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.utils;

import com.ldp.pojos.GioHang;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ACER
 */
public class Utils {

    public static int demSLTour(Map<Integer, GioHang> gioHang) {
        int dem = 0;
        if (gioHang != null) {
            for (GioHang g : gioHang.values()) {
                dem += g.getSoLuong();
            }
        }
        return dem;
    }

    public static Map<String, String> tinhTien(Map<Integer, GioHang> gioHang) {
        BigDecimal tong = BigDecimal.ZERO;
        int dem = 0;

        if (gioHang != null) {
            for (GioHang g : gioHang.values()) {

                dem += g.getSoLuong();
                tong = tong.add(g.getGia().multiply(BigDecimal.valueOf(g.getSoLuong())));

            }
        }
        Map<String, String> kq = new HashMap<>();
        kq.put("tongTien", String.valueOf(tong));
        kq.put("slTour", String.valueOf(dem));

        return kq;
    }

    public static ArrayList<Map<String, String>> Scraping(String url) {

//        String url = "https://dulichvietnam.com.vn/cam-nang-trong-nuoc.html";
        Document doc = null;

        ArrayList<Map<String, String>> kq = new ArrayList<>();

        try {
            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();
            //lấy danh sách các thể <a> chứa bài viết từ kết quả trả về 
            Elements Links = doc.select("div.news");
            Links.remove(Links.size() - 1);
            for (Element element : Links) {
                Map<String, String> list = new HashMap<>();

                list.put("link", String.valueOf(element.getElementsByAttribute("href").attr("href")));
                list.put("src", String.valueOf(element.getElementsByAttribute("src").attr("src")));
                list.put("title", String.valueOf(element.getElementsByAttribute("href").text()));
                list.put("content", String.valueOf(element.getElementsByClass("i-content").text()));
                list.put("date", String.valueOf(element.getElementsByClass("i-date").text()));
                kq.add(list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kq;
    }

    public static ArrayList<Map<String, String>> ScrapingVideo(String url) {

        Document doc = null;

        ArrayList<Map<String, String>> kq = new ArrayList<>();
        try {
            doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(20000).get();
            //lấy danh sách các thể <a> chứa bài viết từ kết quả trả về 
            Elements Links = doc.select("div.video");
            Links.remove(Links.size() - 1);

            for (Element element : Links) {
                Map<String, String> list = new HashMap<>();

                list.put("link" , String.valueOf(element.getElementsByAttribute("href").attr("href")));
                list.put("src" , String.valueOf(element.getElementsByAttribute("src").attr("src")));
                list.put("title" , String.valueOf(element.getElementsByAttribute("alt").attr("alt")));
                list.put("date" , String.valueOf(element.getElementsByClass("i-date").text()));

                kq.add(list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kq;
    }
    
    public static String getBaseURL(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
}
