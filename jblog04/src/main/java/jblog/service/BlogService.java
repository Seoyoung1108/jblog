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
	
	public List<CategoryVo> getCategoriesList(String id) {
		List<CategoryVo> list = blogRepository.findAll(id);
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
	
	public List<PostVo> getPostsList(String id, Long categoryId) {
		List<PostVo> list = null;
		
		if(categoryId==0L) {
			list = blogRepository.findAllPosts(blogRepository.findAll(id).get(0).getId());
		} else {
			list = blogRepository.findAllPosts(categoryId);
		}
		
		return list;
	}
	
	public PostVo getPost(String id, Long categoryId, Long postId) {
		List<PostVo> list = null;
		PostVo vo = null;
		
		if(categoryId==0L && postId==0L) {
			list = blogRepository.findAllPosts(blogRepository.findAll(id).get(0).getId());
			if(list.size()!=0) {
				vo = list.get(0);
			}	
		} else if(postId==0L) {
			list = blogRepository.findAllPosts(categoryId);
			if(list.size()!=0) {
				vo = list.get(0);
			}			
		} else {
			vo = blogRepository.findByCategoryIdAndPostId(categoryId, postId);
		}
		
		return vo;
	}
	
	public void updateBlog(BlogVo blogVo) {
		blogRepository.updateBlog(blogVo);	
	}
	
	public void deletePosts(Long categoryId) {
		blogRepository.deleteByCategoryId(categoryId);
	}
}
