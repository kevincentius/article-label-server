package article;

import org.junit.Test;

import article.util.PasswordUtil;

public class PasswordUtilTest {
	
	@Test
	public void test() {
        System.out.println(PasswordUtil.hash("mypassword"));
	}
	
}
