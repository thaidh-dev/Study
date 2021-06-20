

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Search extends HttpServlet {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public void init() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=java4_lab2_bai1", "thaidhph06986", "dht24111997");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    List lst = new ArrayList<>();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String txt[] = request.getParameterValues("txt");
            String sql = "select * from danh_ba where ";
            String x = request.getParameter("x");
            
                if (x.equals("Tra cứu")) {
                    if (!txt[1].isEmpty()) {
                        sql = sql + "sdt = ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, txt[1]);
                    }
                    else if (!txt[0].isEmpty() && !txt[2].isEmpty() && txt[1].isEmpty()) {
                        sql = sql + "ten like ? and dia_chi like ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, "%" + txt[0]);
                        pst.setString(2, "%" + txt[2] + "%");
                    }
                    else if (!txt[0].isEmpty() && txt[1].isEmpty() && txt[2].isEmpty()) {
                        sql = sql + "ten like ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, "%" + txt[0]);
                    }
                    else if (txt[0].isEmpty() && txt[1].isEmpty() && !txt[2].isEmpty()) {
                        sql = sql + "dia_chi like ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, "%" + txt[2] + "%");
                    }
                    else if (txt[0].isEmpty() && txt[1].isEmpty() && txt[2].isEmpty()) {
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp?x=empty");
                        rd.forward(request, response);
                        return;
                    }

                    rs = pst.executeQuery();

                    out.write("<h1>Kết quả tra điện thoại theo yêu cầu của bạn</h1>");
                    out.println("<table border=1px cellPadding=0px cellSpacing=0px>");
                    out.println("<br>");

                    out.println("<tr><th>ID</th><th>Tên thuê bao</th><th>Số điện thoại</th><th>Địa chỉ</th></tr>");
                    int i = 0;
                    while (rs.next()) {
                        lst.add(rs.getInt(1));
                        out.println("<tr>" +
                            "<td>" + lst.get(i) + "</td>" +
                            "<td>" + rs.getString(2) + "</td>" +
                            "<td>" + rs.getString(3) + "</td>" +
                            "<td>" + rs.getString(4) + "</td>" +
                            "</tr>");
                        i++;
                    }
                    out.println("</table>");          
                }
                else if (x.equals("Delete")) {
                    System.out.println(lst.size());
                    for (int j = 0; j < lst.size(); j++) {
                        pst = con.prepareStatement("delete danh_ba where id = ?");
                        pst.setInt(1, (int) lst.get(j));
                        pst.executeUpdate();
                    }
                    out.println("<h1>Xóa thành công</h1>");
                }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void destroy() {
        try {
            con.close();
            pst.close();
            rs.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
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
    public String getServletInfo() {
        return "Short description";
    }
}
