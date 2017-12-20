package co.com.sbaqueroadev.contigo.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;



/**
 * @author sergio
 * Spring MVC Configuration
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer{

	private static final Logger logger = LoggerFactory.getLogger(MvcConfiguration.class);

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureViewResolvers(org.springframework.web.servlet.config.annotation.ViewResolverRegistry)
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		logger.info("Setting view resolver...");
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	} 


	/********************* WEBJARS *****************************************/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/")
			.resourceChain(false);
		}
		/*if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    RESOURCE_LOCATIONS);
        }*/
	}

	/********************* INTERNATIONALIZATION ********************************/

	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public CookieLocaleResolver localeResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		localeResolver.setCookieName("my-locale-cookie");
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeInterceptor(){
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
	}
}
