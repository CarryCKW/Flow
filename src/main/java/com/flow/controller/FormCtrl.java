package com.flow.controller;

//import com.alibaba.fastjson.JSONObject;
import com.flow.combination.String2Time;
import com.flow.repository.User;
import com.flow.repository.Vocation;
import com.flow.service.FormInfo;
import com.flow.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 蔡小蔚
 */
@Controller
@RequestMapping("/form")
public class FormCtrl {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private FormInfo formInfo;

    @RequestMapping(value = "/addVocation")
    public void insertVocation(HttpServletRequest request, HttpServletResponse response, @Validated Vocation vocation) throws IOException {
        System.out.println("in method insertVocation" );
        System.out.println(vocation.toString());

        Timestamp timestamp = null;
        String s = vocation.getStime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
//            timestamp = (Timestamp) format.parse(s);
            timestamp = Timestamp.valueOf(s);
            vocation.setStarttime(timestamp);
            vocation.setCreatedate(new Timestamp(System.currentTimeMillis()));
            vocation.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
            vocation.setFormtype(1);
            vocation.setFormstatus(0);
            Cookie[] cookies = request.getCookies();
            String nick = null;
            for (Cookie cookie : cookies){
                if (cookie.getName().equalsIgnoreCase("nick")) {
                    nick = cookie.getValue();
                    break;
                }
            }
            vocation.setNick(nick);
            System.out.println("after process in method insertVocation " + vocation.toString() + " uuid:" + vocation.getUuid());
            //use forminfo service
            formInfo.insertVocation(vocation);
            //redirece to vocationlist
//            String basePath = request.getContextPath();
//            basePath.replaceAll("form", "");
            response.sendRedirect(request.getContextPath() + "/vacationinfo");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/form/displayVocation");
        }

//        String nickname = (String)request.getParameter("nickname");
//        String stime = (String)request.getParameter("starttime");
//        stime += " 08:00:00";
//        Timestamp starttime = Timestamp.valueOf(stime);
//        String ltime = (String)request.getParameter("lasttime");
//        int lasttime = Integer.parseInt(ltime);
//        String descript = (String)request.getParameter("desc");
//
//
//
//        //generate form and vocation next
//        Vocation vocation = new Vocation();
//        vocation.setDescript(descript);
//        vocation.setStarttime(starttime);
//        vocation.setLasttime(lasttime);
//        vocation.setNick(nickname);
//        vocation.setCreatedate(new Timestamp(System.currentTimeMillis()));
//        vocation.setFormtype(1);
//        vocation.setFormstatus(0);
//        vocation.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
//        System.out.println("call the method insertVocation, the uuid is:" + vocation.getUuid());
//        formInfo.insertVocation(vocation);

    }

    @RequestMapping("/displayVocation")
    public ModelAndView displayVocation(){
        System.out.println("in displayVocation...");
        ModelAndView view = new ModelAndView("vocation-add");
        view.addObject("newVocation", new Vocation());
        return view;
    }

}
