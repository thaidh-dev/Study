package com.poly;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Now extends SimpleTagSupport {
    @Override
    public void doTag() {
        try {
            JspWriter out = getJspContext().getOut();
            Date now = new Date();
            SimpleDateFormat formater = new SimpleDateFormat();
            formater.applyPattern("dd/MM/yyyy");
            String time = formater.format(now);
            out.println("<h1>Ngày hôm nay là: " + time + "</h1>");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
