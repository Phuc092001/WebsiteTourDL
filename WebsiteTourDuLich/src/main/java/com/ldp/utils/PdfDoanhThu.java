/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldp.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class PdfDoanhThu extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"doanh-thu.pdf\"");
        
        @SuppressWarnings("unchecked")
        List<Object[]> list = (List<Object[]>) model.get("list");
        Font bold = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD);
        Paragraph header = new Paragraph("THỐNG KÊ DOANG THU THEO TOUR", bold);
        header.setAlignment(Element.ALIGN_CENTER);
        
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        Paragraph date = new Paragraph(String.valueOf(sdf.format(d)));
        date.setAlignment(Element.ALIGN_RIGHT);
        
        Table table = new Table(3);
        table.addCell("   MÃ TOUR");
        table.addCell("   TÊN TOUR");
        table.addCell("   DOANH THU");

        for (Object[] tour : list) {
            table.addCell(String.valueOf(tour[0]));
            table.addCell(String.valueOf(tour[1]));
            table.addCell(String.valueOf(tour[2]));
        }
        document.add(header);
        document.add(date);
        document.add(table);
    }

}
