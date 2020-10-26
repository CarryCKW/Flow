package com.flow.configuration;

import com.flow.flowdefinition.FlowDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 蔡小蔚
 */
@Configuration
@ComponentScan(basePackages = {"com.flow"})
public class TranscationConfig {

    @Bean
    public FlowDefinition flowDefinition(){
        FlowDefinition flowDefinition = new FlowDefinition();
        return flowDefinition;
    }
}
