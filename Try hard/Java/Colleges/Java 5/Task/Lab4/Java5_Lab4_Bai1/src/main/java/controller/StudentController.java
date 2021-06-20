package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class StudentController {
    @Autowired
    ServletContext application;

    @RequestMapping(value = "")
    public String index(HttpServletRequest request, HttpSession session) {
        File folder = new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 5\\Task\\Lab4\\Java5_Lab4_Bai1\\src\\main\\webapp\\resources\\images");

        if (folder.isDirectory()) {
            File file[] = folder.listFiles();
            String tenFile[] = new String[file.length];

            for (int i = 0; i < file.length; i++) {
                tenFile[i] = file[i].getName();
            }

            request.setAttribute("photo", tenFile);
            application.setAttribute("soAnh", file.length);
        }

        return "index";
    }
}
