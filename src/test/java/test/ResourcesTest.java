package test;


import com.flow.flowdefinition.FlowDefinition;

import java.io.IOException;

public class ResourcesTest {
    public static void main(String[] args) throws IOException {
        System.out.println(FlowDefinition.getFlowDefinition(FlowDefinition.CHOICE.Vocation));

    }
}
