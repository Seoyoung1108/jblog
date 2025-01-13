package jblog.vo;

public class PostVo {
	private Long id;
	private String title;
	private String contents;
	private String regDate;
	private Long categoryId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "PostVo [id=" + id + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryId=" + categoryId + "]";
	}
	
	
}
