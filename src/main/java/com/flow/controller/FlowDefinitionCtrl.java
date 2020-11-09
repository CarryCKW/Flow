package com.flow.controller;

import com.flow.flowdefinition.FlowDefinition;
import com.flow.repository.FlowDefInfo;
import com.flow.repository.Vocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author 蔡小蔚
 */
@Controller
@RequestMapping("/flowdefine")
public class FlowDefinitionCtrl {

    @Autowired
    private FlowDefinition flowDefinition;

    @RequestMapping("/vocationflow")
    public ModelAndView vocationflowView() throws IOException {
        ModelAndView view = new ModelAndView("flowdefinition");
        String sourceResult = null;
        try{
            FlowDefInfo flowDefInfo = new FlowDefInfo();
            sourceResult = flowDefInfo.getBeforeChangeDescript();
            view.addObject("flowdefinition", flowDefInfo);
            view.addObject("sourceResult", sourceResult);
            System.out.println("sourceResult:" + sourceResult);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return view;
    }
    @RequestMapping("/changeflow")
    public void changeflow(String desc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            FlowDefInfo flowDefInfo = new FlowDefInfo();
            String str=new String() ;
            str=desc;
            System.out.println(str);
            FlowDefinition .cout2File(FlowDefinition.CHOICE.Vocation ,str) ;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/flowdefine/vocationflow");
    }
}
