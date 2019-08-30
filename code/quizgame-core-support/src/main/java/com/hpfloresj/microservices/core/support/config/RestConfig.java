package com.hpfloresj.microservices.core.support.config;

import com.hpfloresj.microservices.core.support.rest.client.RestClientConfig;
import com.hpfloresj.microservices.core.support.rest.swagger.SwaggerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@ComponentScan(basePackages = {"com.hpfloresj.microservices.core.support.rest"})
@Import({
        SwaggerConfig.class,
        RestClientConfig.class
})
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RestConfig {

}
