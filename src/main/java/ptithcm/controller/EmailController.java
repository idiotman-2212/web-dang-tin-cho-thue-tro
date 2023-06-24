package ptithcm.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.service.EmailService;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    MailSender mailSender;
    @RequestMapping("/index")
    public String index(ModelMap model){
        this.guimail();
        return "test";
    }
    void guimail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("laiducmanh01@gmail.com");
        message.setSubject("demo");
        message.setText("123");
        mailSender.send(message);
    }
    
}
