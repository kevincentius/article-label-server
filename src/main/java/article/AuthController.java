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
    		@RequestBody(required=false) Account account
    ) {
        accountRepository.save(account);
    }

}
