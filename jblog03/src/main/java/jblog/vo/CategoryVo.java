package jblog.vo;

public class CategoryVo {
	private Long id;
	private String name;
	private String description;
	private String blogId;
	private int postNum;
	
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
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	@Override
	public String toString() {
		return "CategoryVo [id=" + id + ", name=" + name + ", description=" + description + ", blogId=" + blogId
				+ ", postNum=" + postNum + "]";
	}
}
