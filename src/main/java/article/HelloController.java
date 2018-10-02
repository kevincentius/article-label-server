package article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired private AccountRepository accountRepository;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/dbtest")
    public String dbtest() {
    	Account user = new Account();
    	user.setName("testuser1");
    	user.setEmail("testuser1@testmail.com");
    	user.setPassword(PasswordUtil.hash("password1"));
		accountRepository.save(user);
		
        return "DBTEST";
    }

    @RequestMapping(value = "/public/register", method = RequestMethod.POST)
    public void register(
    		@RequestBody Account account
    ) {
        accountRepository.save(account);
    }

    @RequestMapping(value = "/public/test", method = RequestMethod.GET)
    public String test() {
        return "Test public GET";
    }
    
}
