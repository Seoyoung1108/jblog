package jblog.vo;

public class BlogVo {
	private String title;
	private String profile;
	private String blogId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "BlogVo [title=" + title + ", profile=" + profile + ", blogId=" + blogId + "]";
	}
	
}
