package Controller;

import Model.CheckLogin;
import java.io.*;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Search extends HttpServlet {
    CheckLogin c = new CheckLogin();

    public void init() throws ServletException {
        c.lienKetSql();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            //PrintWriter out = response.getWriter();
            
            String x = request.getParameter("x");
            if (x != null) {
                if (x.equals("tryAgain")) {
                    RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
                    rd.forward(request, response);
                }
                else if (x.equals("Login")) {
                    String txt[] = request.getParameterValues("txt");
                    if (c.check(txt[0], txt[1])) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("USER", c.ten(txt[0]));
                        RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
                        rd.forward(request, response);
                    }
                    else {
                        RequestDispatcher rd = request.getRequestDispatcher("Fail.jsp");
                        rd.forward(request, response);
                    }
                }
                else if (x.equals("Search")) {
                    String ten = request.getParameter("text");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("TEN", ten);
                    response.sendRedirect("Welcome.jsp?lol=true");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        c.destroy();
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
