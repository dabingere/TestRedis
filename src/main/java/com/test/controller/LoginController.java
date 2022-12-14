package com.test.controller;

import com.test.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

   public String doLogin(User user){
       Subject subject = SecurityUtils.getSubject();
       UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
       subject.login(token);

       return "main";
   }
}
