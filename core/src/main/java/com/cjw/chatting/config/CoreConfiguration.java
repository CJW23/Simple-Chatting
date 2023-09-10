package com.cjw.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {"application-core.yml"}, factory = YamlLoadFactory.class)
public class CoreConfiguration {
}
