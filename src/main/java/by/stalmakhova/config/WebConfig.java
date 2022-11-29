package by.stalmakhova.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean public ClassLoaderTemplateResolver templateResolver() {
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    @Bean public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    @Bean
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    @Override public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("registeration");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/master").setViewName("masters");
        registry.addViewController("/pet").setViewName("pets");
        registry.addViewController("/history").setViewName("history");
        registry.addViewController("/procedure").setViewName("procedures");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/masterAdmin").setViewName("masterAdmin");
        registry.addViewController("/procedureAdmin").setViewName("procedureAdmin");

    }
}

