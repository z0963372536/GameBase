package tw.twcoding.leolee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Controller
@EnableWebMvc
@ComponentScan(basePackages = {})
@Import({tw.twcoding.leolee.view.LoginSystemViewJavaConfig.class,
		 tw.twcoding.leolee.view.BasicViewJavaConfig.class})
public class SpringMVCJavaConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
	}
	
	@Bean
	public ViewResolver beanNameResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(10);
		return beanNameViewResolver;
	}
}
