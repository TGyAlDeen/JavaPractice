package jp.co.calace.redwing;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	public static final String URL_PATH ="src/main/resources/public/upl/";
	// finall path will be on tomcat server as :
	// /opt/tomcat/webapss/redwing/WEB-INF/classes/public/upl/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		System.out.println("registery of WebMvc");
		registry.addResourceHandler("/upl/**")
		.addResourceLocations("file:"+URL_PATH)
		.setCacheControl(CacheControl.noCache());
	}

}
