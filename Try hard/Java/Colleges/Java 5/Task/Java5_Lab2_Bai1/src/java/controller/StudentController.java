package controller;

import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
    @RequestMapping(value="form", method=RequestMethod.GET)
    public String showForm() {
        return "form";
    }
    
    @RequestMapping(value="info", method=RequestMethod.POST)
    public String showInfo(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } 
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        
        return "info";
    }
}
