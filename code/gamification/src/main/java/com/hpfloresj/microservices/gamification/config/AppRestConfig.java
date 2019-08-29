package com.hpfloresj.microservices.gamification.config;

import com.hpfloresj.microservices.core.support.config.RestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RestConfig.class})
public class AppRestConfig {
}
