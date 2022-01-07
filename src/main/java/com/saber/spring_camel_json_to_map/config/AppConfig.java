package com.saber.spring_camel_json_to_map.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.component.micrometer.messagehistory.MicrometerMessageHistoryFactory;
import org.apache.camel.component.micrometer.routepolicy.MicrometerRoutePolicyFactory;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE,false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper;
    }
    @Bean
    public CamelContextConfiguration camelContextConfiguration(){
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {

                camelContext.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
                camelContext.addRoutePolicyFactory(new MicrometerRoutePolicyFactory());
                camelContext.setMessageHistoryFactory(new MicrometerMessageHistoryFactory());
            }
            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }

}
