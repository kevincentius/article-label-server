package article;

import org.junit.Test;

public class PasswordUtilTest {
	
	@Test
	public void test() {
        System.out.println(PasswordUtil.hash("mypassword"));
	}
	
}
