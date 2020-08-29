package tw.twcoding.leolee.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
public class BasicViewJavaConfig {

	@Bean
	public View indexView() {
		RedirectView view = new RedirectView();
		view.setContextRelative(true);
		view.setUrl("/");
		return view;
	}
}
