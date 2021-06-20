package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("a")
    public String showForm() {
        return "user/login";
    }
    
    @RequestMapping("b")
    public String login(HttpServletRequest request) {
        String id = request.getParameter("id");
        String pw = request.getParameter("password");
        if (id.equals("fpt") && pw.equals("polytechnic")) {
            request.setAttribute("uid", id);
            request.setAttribute("pwd", pw);
            return "user/info";
        }
        request.setAttribute("message", "Sai thông tin đăng nhập");
        return "user/login";
    }
}
