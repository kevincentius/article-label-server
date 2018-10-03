package article.dto;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountDTO, Long> {

	<T extends AccountDTO> T save(T entity);
	AccountDTO findByName(String name);
	
}
