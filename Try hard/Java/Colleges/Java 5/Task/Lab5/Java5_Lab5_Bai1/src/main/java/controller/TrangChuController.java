package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrangChuController {
    @RequestMapping("/")
    public String showInfo() {
        return "info";
    }

    @RequestMapping("myLoginForm")
    public String showMyLoginForm() {
        return "login";
    }
}
