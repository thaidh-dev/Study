package Controller;

import DAO.CustomerDAO;
import fpoly.entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ControllerCustomer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            
            if (action.equals("Login")) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                CustomerDAO cus = new CustomerDAO();
                boolean check = cus.checkLogin(user, pass);
                String url = "error.jsp";
                if (check == true) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER", user);
                    url = "search.jsp";
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("Search")) {
                String tenkh = request.getParameter("txtTenKH");
                List<Customers> list = CustomerDAO.getListCustomers(tenkh);
                request.setAttribute("listKH", list);
                String url = "search.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("New")) {
                RequestDispatcher rd = request.getRequestDispatcher("new.jsp");
                rd.forward(request, response);
            }
            else if (action.equals("Insert")) {
                String makh = request.getParameter("txtMaKH");
                String matkhau = request.getParameter("txtMatKhau");
                String hoten = request.getParameter("txtHoten");
                String email = request.getParameter("txtEmail");
                String sodt = request.getParameter("txtSoDT");
                Customers newkh = new Customers(makh, matkhau, hoten, email, sodt);
                CustomerDAO.insertCustomer(newkh);
                RequestDispatcher rd = request.getRequestDispatcher("new.jsp");
                rd.forward(request, response);
            }
            else if (action.equals("Delete")) {
                String makh = request.getParameter("txtmaKH");
                boolean daxoa = CustomerDAO.deleteCustomer(makh);
                if (daxoa) {
                    String url = "ControllerCustomer?txtTenKH=&action=Search";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
