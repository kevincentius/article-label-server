package article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

	@Autowired private AccountRepository accountRepository;
	
	private HandlerInterceptor authInterceptor = new HandlerInterceptor() {
		@Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {

	        String name = request.getHeader("Username");
	        String password = request.getHeader("Password");
	        
	        Account account = accountRepository.findByName(name);
	        if (account != null && PasswordUtil.validate(password, account.getPassword())) {
	        	return true;
	        } else {
	        	response.setStatus(HttpStatus.FORBIDDEN.value());
	        	return false;
	        }
	        
	    }
	};
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
	
}
