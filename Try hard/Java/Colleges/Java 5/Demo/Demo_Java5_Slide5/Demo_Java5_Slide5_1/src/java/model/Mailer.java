package model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Component("mailer")
public class Mailer {
    public String send() {
        return "Đang gửi mail";
    }
}
