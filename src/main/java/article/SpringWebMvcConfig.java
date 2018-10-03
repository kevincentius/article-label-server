package article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import article.dto.AccountDTO;
import article.dto.AccountRepository;
import article.util.PasswordUtil;

@EnableWebMvc
@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

	@Autowired private AccountRepository accountRepository;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    /**
     * This will serve the src/main/resources/public folder as static content.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    /**
     * Only requests originating from url paths listed here will be allowed to access
     *  this server by the browser.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**");
    }
    
    /**
     * Checks the "Username" and "Password" HTTP Headers and puts an AccountDTO as a request attribute.
     */
	private HandlerInterceptor authInterceptor = new HandlerInterceptor() {
		@Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
			if ("OPTIONS".equals(request.getMethod())) {
				// CORS pre-flight request does not yet contain authentication headers
				return true;
			} else {
		        String name = request.getHeader("Username");
		        String password = request.getHeader("Password");
		        
		        AccountDTO account = accountRepository.findByName(name);
		        request.setAttribute("account", account);
		        if (account != null && PasswordUtil.validate(password, account.getPassword())) {
		        	return true;
		        } else {
		        	response.setStatus(HttpStatus.FORBIDDEN.value());
		        	return false;
		        }
			}
	    }
	};
	
	/**
	 * Define which url patterns are secured by authInterceptor.
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
        		.addPathPatterns("/**")
        		.excludePathPatterns("/public/**")
        		.excludePathPatterns("/auth/**")
        		.excludePathPatterns("/error");
    }
	
}
