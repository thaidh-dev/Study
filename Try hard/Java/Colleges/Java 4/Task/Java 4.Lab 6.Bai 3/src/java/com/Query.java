package com;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Query extends SimpleTagSupport {
    String var;
    Connection dataSource;
    ResultSet rs;
    
    @Override
    public void doTag() {
        try {
            StringWriter sw = new StringWriter();
            getJspBody().invoke(sw);
            PreparedStatement pst = dataSource.prepareStatement(sw.toString());
            rs = pst.executeQuery();
            
            JspWriter out = getJspContext().getOut();

            out.print("<table border='1'>");
            while (rs.next()) {
                out.print("<tr>"
                        + "<td>" + rs.getString(1) + "</td>"
                        + "<td>" + rs.getString(2) + "</td>"
                        + "<td>" + rs.getString(3) + "</td>"
                        + "</tr>");
            }
            out.print("</table>");
            
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Connection getDataSource() {
        return dataSource;
    }

    public void setDataSource(Connection dataSource) {
        this.dataSource = dataSource;
    }
}
