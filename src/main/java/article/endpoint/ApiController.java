package article.endpoint;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import article.dto.AccountDTO;
import article.dto.ArticleDTO;
import article.dto.ChoiceDTO;
import article.dto.LabelDTO;
import article.dto.QuestionDTO;
import article.dto.SubmitDTO;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/next-article", method = RequestMethod.GET)
    public ArticleDTO nextArticle(
    		@RequestAttribute("account") AccountDTO account
    ) {
    	String sql = getResourceFileAsString("sql/next-article.sql");
    	List<ArticleDTO> result = jdbcTemplate.query(sql, new Object[] {account.getId()}, (rs, rn) -> {
    		ArticleDTO article = new ArticleDTO();
    		article.setId(rs.getLong("id"));
    		article.setTitle(rs.getString("title"));
    		article.setContent(rs.getString("content"));
    		return article;
    	});
    	
    	if (result.size() == 1) {
    		return result.get(0);
    	} else if (result.size() == 0) {
    		return null;
    	} else {
    		throw new RuntimeException("Multiple results from next-article.");
    	}
    }

    @RequestMapping(value = "/labels", method = RequestMethod.GET)
    public List<QuestionDTO> getLabels() {
    	List<QuestionDTO> questions = jdbcTemplate.query("SELECT * FROM question", (rs, rn) -> {
    		QuestionDTO question = new QuestionDTO();
    		question.setId(rs.getLong("id"));
    		question.setName(rs.getString("name"));
    		question.setDescription(rs.getString("description"));
    		question.setChoices(new ArrayList<>());
    		return question;
    	});
    	
    	Map<Long, QuestionDTO> questionMap = new HashMap<>();
    	for (QuestionDTO question : questions) {
    		questionMap.put(question.getId(), question);
    	}
    	
    	jdbcTemplate.query("SELECT * FROM choice", (rs, rn) -> {
    		ChoiceDTO choice = new ChoiceDTO();
    		choice.setId(rs.getLong("id"));
    		choice.setName(rs.getString("name"));
    		
    		questionMap.get(rs.getLong("question")).getChoices().add(choice);
    		return choice;
    	});
    	
    	return questions;
    }

    /**
     * Saves the submitted labels into the database.
     * 
     * Returns the next article.
     * @return 
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ArticleDTO submitLabels(
    		@RequestAttribute("account") AccountDTO account,
    		@RequestBody SubmitDTO submitDTO
    ) {
    	List<Object[]> batchArgs = new ArrayList<>();
		for (LabelDTO label : submitDTO.getLabels()) {
			batchArgs.add(new Object[] {
					submitDTO.getArticle(),
					account.getId(),
					label.getQuestion(),
					label.getChoice()
			});
		}
		
		String sql = "INSERT INTO label (article, account, question, choice) VALUES (?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, batchArgs);
		
		return nextArticle(account);
    }
    
    /**
     * source: https://stackoverflow.com/a/46613809/2015374
     * Reads given resource file as a string.
     *
     * @param fileName the path to the resource file
     * @return the file's contents or null if the file could not be opened
     */
    public String getResourceFileAsString(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return null;
    }
    
}
