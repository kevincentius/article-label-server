package article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired private AccountRepository accountRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(
    		@RequestBody(required=false) AccountDTO account
    ) {
    	account.setPassword(PasswordUtil.hash(account.getPassword()));
        accountRepository.save(account);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(
    		@RequestBody(required=false) AccountDTO account
    ) {
        AccountDTO accountEntity = accountRepository.findByName(account.getName());
        if (accountEntity == null) {
        	return false;
        } else {
        	String hash = accountEntity.getPassword();
        	return PasswordUtil.validate(account.getPassword(), hash);        	
        }
    }

}
