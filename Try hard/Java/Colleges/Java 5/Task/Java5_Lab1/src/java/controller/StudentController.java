package controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @RequestMapping("form")
    public String showForm() {
        return "form";
    }
    
    @RequestMapping("info")
    public String showInfo(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } 
        catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
        
        return "info";
    }
    
}
