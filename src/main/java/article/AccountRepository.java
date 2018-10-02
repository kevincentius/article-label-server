package article;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	<T extends Account> T save(T entity);
	Account findByName(String name);
	
}
