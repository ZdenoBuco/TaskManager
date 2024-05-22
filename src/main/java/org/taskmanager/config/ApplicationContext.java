package org.taskmanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.taskmanager.utilities.resolvers.AuthIdentityInDTOArgumentResolver;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"org.taskmanager"})
@EnableWebMvc
@RequiredArgsConstructor
public class ApplicationContext implements WebMvcConfigurer {
    private final AuthIdentityInDTOArgumentResolver authIdentityArgumentResolver;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("default");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authIdentityArgumentResolver);
    }
}
