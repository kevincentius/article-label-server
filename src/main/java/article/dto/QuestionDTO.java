package article.dto;

import java.util.List;

public class QuestionDTO {
	
	private Long id;
	private String name;
	private String description;
	private List<ChoiceDTO> choices;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ChoiceDTO> getChoices() {
		return choices;
	}
	public void setChoices(List<ChoiceDTO> choice) {
		this.choices = choice;
	}
	
}
