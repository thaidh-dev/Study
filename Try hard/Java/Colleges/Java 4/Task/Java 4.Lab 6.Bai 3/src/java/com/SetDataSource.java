package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SetDataSource extends SimpleTagSupport {
    String driver;
    String url;
    String user;
    String pass;
    String var;

    @Override
    public void doTag() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);

            getJspContext().setAttribute(var, con);  
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
