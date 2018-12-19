package com.dicka.authcloudoauth2resource.config;

import com.dicka.authcloudoauth2resource.model.CustomPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class ConfigWeb implements WebMvcConfigurer{

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> handlerResolvers){
        handlerResolvers.add(currentHandlerArgumentResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver currentHandlerArgumentResolver(){
        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter methodParameter) {
                return methodParameter
                        .getParameterType().equals(CustomPrincipal.class);
            }

            @Override
            public Object resolveArgument(MethodParameter methodParameter,
                                          ModelAndViewContainer modelAndViewContainer,
                                          NativeWebRequest nativeWebRequest,
                                          WebDataBinderFactory webDataBinderFactory)
                    throws Exception {

                try{

                    return (CustomPrincipal) SecurityContextHolder
                            .getContext().getAuthentication()
                            .getPrincipal();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }
}
