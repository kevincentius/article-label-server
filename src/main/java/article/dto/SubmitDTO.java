package article.dto;

public class SubmitDTO {
	
	private Long article;
	private LabelDTO[] labels;
	
	public Long getArticle() {
		return article;
	}
	public void setArticle(Long article) {
		this.article = article;
	}
	public LabelDTO[] getLabels() {
		return labels;
	}
	public void setLabels(LabelDTO[] labels) {
		this.labels = labels;
	}
	
}
