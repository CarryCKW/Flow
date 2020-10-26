package com.flow.controller;

import com.flow.flowdefinition.FlowDefinition;
import com.flow.repository.FlowDefInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author 蔡小蔚
 */
@Controller
@RequestMapping("/flowdefine")
public class FlowDefinitionCtrl {

    @Autowired
    private FlowDefinition flowDefinition;

    @RequestMapping("/showflowdefinition")
    public ModelAndView vocationflowView() throws IOException {
        ModelAndView view = new ModelAndView("flowdefinition");
        FlowDefInfo flowDefInfo = new FlowDefInfo();
        view.addObject("flowdefinition", flowDefInfo);
        return view;
    }
}
