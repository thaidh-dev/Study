package controller;

import model.Company;
import model.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class HomeController {
    @Autowired @Qualifier("lol")
    Company company;

    @Autowired
    Mailer mailer;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("company", company);
        return "index";
    }

    @RequestMapping("send")
    public String send(Model model) {
        model.addAttribute("sendMail", mailer.send());
        return "index";
    }
}
