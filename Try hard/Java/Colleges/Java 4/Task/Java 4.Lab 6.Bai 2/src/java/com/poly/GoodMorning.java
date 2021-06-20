/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Admin
 */
public class GoodMorning extends SimpleTagSupport {

    private String name;
    private String pattern;
    private int so;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String nowToString = format.format(now);
        StringWriter sw = new StringWriter();
        
        try {
            getJspContext().setAttribute("so", so);  
            
            out.println("<h2>Chào buổi sáng: " + name + "</h2>");
            out.println("<h2>Hôm nay là: " + nowToString + "</h2>");
            out.println("<h2>Số " + so + "</h2>");
            out.println("<h2>Mừng bạn đến với Website</h2>");

            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw);
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }
}
