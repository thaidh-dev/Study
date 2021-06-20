package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // đánh dấu là file config
@EnableWebMvc // <mvc:annotation-driven/>
@ComponentScan(basePackages = {"controller", "config"}) // <context:component-scan base-package=""/>
public class DispatcherServlet {
    @Bean // tạo 1 bean view trong dispatcher-servlet
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

}
