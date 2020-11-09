package com.flow.controller;

import com.flow.flowdefinition.FlowDefinition;
import com.flow.repository.User;
import com.flow.repository.Vocation;
import com.flow.service.FormInfo;
import com.flow.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 蔡小蔚
 */
@Controller
public class LoginCtrl {

    private String usernick;
    @Autowired
    private UserInfo userInfo;

    @Autowired
    private FlowDefinition flowDefinition;

    @Autowired
    private FormInfo formInfo;

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
                response.sendRedirect(request.getContextPath() + "/index");
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
    public  ModelAndView toVacationView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        ModelAndView view = null;
        System.out.println("cookies num" + cookies.length);
        for (Cookie cookie : cookies){
            if (cookie.getName().equalsIgnoreCase("nick")){
                usernick = cookie.getValue();
                User user = userInfo.getUserByNick(usernick);
                System.out.println("before toIndexView, usernick:" + user.getNick() + " status: " + user.getUserstatus());
                if(user.getUserstatus() == 2) {
                    System.out.println("stu in");
                    //加入请假消息列表
                    view = new ModelAndView("order-list-stu");

                    List<Vocation> vocationList = (List<Vocation>) formInfo.getVocationListByNick(usernick);
                    int count=flowDefinition .GetNodesNum() ;
                    int pass = (int)(Math .pow(2,count)-1);
                    System.out.println("pass = "+pass);
                    view.addObject("vocationlist", vocationList);
                    view.addObject("pass",pass) ;
                    return view;
                } else if (user.getUserstatus() == 1) {
                    System.out.println("admin in");
                    //加入请假消息列表
                    view = new ModelAndView("order-list-admin");
                    usernick  = user.getNick() ;
                    int count;
                    int index = FlowDefinition .getCurrentNode(usernick).index ;
                    List<Vocation> vocationlist = (List<Vocation>) formInfo.getFormsByAdminName(usernick,FlowDefinition.CHOICE.Vocation) ;
                    count = (int) vocationlist.stream().filter(vocation1 -> vocation1.getFormstatus() == 0).count();
                    List<FlowDefinition .Node > prenodes = FlowDefinition .getPreNodes(usernick);
                    int result=0;
                    for(int i=0;i<prenodes.size() ;i++)
                    {
                        if (prenodes .get(i).index!=0){
                            result += Math.pow(2,prenodes.get(i).index -1);
                        }
                    }
                    System.out.println("status = "+result );
                    view.addObject("vocationlist", vocationlist);
                    view.addObject("count", count);
                    view.addObject("status",result) ;
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

    @RequestMapping(value="/pass/{id}")
    public void passvacation(HttpServletResponse response,HttpServletRequest request,@PathVariable String id) throws IOException {
        System.out.println("in method passvocation" +usernick );
        Vocation vocation = formInfo.Getvovationbyid(id);
        System.out.println(vocation.toString());
        formInfo.updateForm(vocation ,usernick ,true) ;
        response.sendRedirect(request.getContextPath() + "/vacationinfo");
    }

    @RequestMapping(value="/ban/{id}")
    public void banvacation(HttpServletResponse response,HttpServletRequest request,@PathVariable String id) throws IOException {
        System.out.println("in method banvocation" );
        Vocation vocation = formInfo.Getvovationbyid(id);
        formInfo.updateVocation(vocation ,usernick ,false);
        response.sendRedirect(request.getContextPath() + "/vacationinfo");
    }

}
