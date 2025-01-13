package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Service
public class BlogService {
private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}

	public BlogVo getContents(String id) {
		BlogVo vo = blogRepository.findById(id);
		return vo;
	}

	public List<CategoryVo> getCategoriesList() {
		List<CategoryVo> list = blogRepository.findAll();
		return list;
	}

	public void deleteCategory(String id, Long categoryId) {
		blogRepository.deleteByIdAndCategoryId(id, categoryId);	
	}
	
	public void addCategory(CategoryVo vo) {
		blogRepository.insert(vo);
	}

	public void addWrite(PostVo vo) {
		blogRepository.insertPost(vo);	
	}

	public List<PostVo> getPostsList(Long categoryId) {
		List<PostVo> list = blogRepository.findAllPosts(categoryId);
		return list;
	}

	public PostVo getPost(Long categoryId, Long postId) {
		PostVo vo = blogRepository.findByCategoryIdAndPostId(categoryId, postId);
		return vo;
	}
}
