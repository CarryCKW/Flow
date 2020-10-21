package com.flow.controller;

import com.flow.repository.User;
import com.flow.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 蔡小蔚
 */
@Controller
public class LoginCtrl {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping("/")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("loginform");
        view.addObject("user", new User());
        return view;
    }

    @RequestMapping(value = "/login")
    public void afterLogin(HttpServletRequest request, HttpServletResponse response, @Validated User user, BindingResult result) throws IOException {
        System.out.println("after login");
        String nick = user.getNick();
        String userpassword = user.getPassword();
        System.out.println("username= " + nick);
        System.out.println("password= " + userpassword);

        try {
            if(userInfo.checkPassword(nick,userpassword)){
                response.addCookie(new Cookie("nick",nick));
                System.out.println("pass login");
                response.sendRedirect(request.getContextPath() + "/index");  // wrong
            }else {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                //out.print("<script language=\"javascript\">alert('密码或用户名错误！');window.location.href="+request.getContextPath()+"/loginForm</script>");
                response.sendRedirect(request.getContextPath() + "/");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

    @RequestMapping("/index")
    public ModelAndView toIndexView(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping("/vacationinfo")
    public  ModelAndView toVacationView(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        ModelAndView view = null;
        System.out.println("cookies num" + cookies.length);
        for (Cookie cookie : cookies){
            if (cookie.getName().equalsIgnoreCase("nick")){
                String usernick = cookie.getValue();
                User user = userInfo.getUserByNick(usernick);
                System.out.println("before toIndexView, usernick:" + user.getNick() + " status: " + user.getUserstatus());
                if(user.getUserstatus() == 2) {
                    System.out.println("stu in");
                    //加入请假消息列表
                    view = new ModelAndView("order-list-stu");
                    //view.add(...)
                    return view;
                } else if (user.getUserstatus() == 1) {
                    System.out.println("admin in");
                    //加入请假消息列表
                    view = new ModelAndView("order-list-admin");
                    //view.add(...)
                    return view;
                } else if (user.getUserstatus() == 0) {
                    System.out.println("root view not implement!");
                    view = new ModelAndView("null");
                    //view.add(...)
                    return view;
                }
            }
        }
        view = new ModelAndView("null");
        //view.add(...)
        return view;
    }




}
