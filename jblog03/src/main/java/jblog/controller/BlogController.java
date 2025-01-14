package jblog.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.service.BlogService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.service.FileuploadService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	private final FileuploadService fileUploadService;
	private final BlogService blogService;
	private final ServletContext servletContext;
	private final ApplicationContext applicationContext;
	
	public BlogController(BlogService blogService, FileuploadService fileUploadService, ServletContext servletContext, ApplicationContext applicationContext) {
		this.blogService=blogService;
		this.fileUploadService=fileUploadService;
		this.servletContext = servletContext;
		this.applicationContext = applicationContext;
	}
	
	/*
	@RequestMapping({"","/{path1}","{path1},{path2}"})
	public String main(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1, @PathVariable("path2") Optional<Long> String path2) {
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()){
			categoryId = path1.get();
			postId = path2.get();
		} else i(path1.isPresent()){
			categoryId = path1.get();
		}
		
		// 서비스에서 하기
		// categoryId = 0L => 기본 카테고리 번호 세팅
		// postId도

		return "blog/main";
	}
	*/
	
	@RequestMapping("")
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(id);
		List<PostVo> postVoList = blogService.getPostsList(categoryVoList.get(0).getId());
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVoList", categoryVoList);
		
		if(postVoList.size()!=0) {
			model.addAttribute("postVoList", postVoList);
			model.addAttribute("targetPost", postVoList.get(0));
		}	

		return "blog/main";
	}
	
	@RequestMapping("/{categoryId}")
	public String main(@PathVariable("id") String id, @PathVariable("categoryId") Long categoryId, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(id);
		List<PostVo> postVoList = blogService.getPostsList(categoryId);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVoList", categoryVoList);
		
		if(postVoList.size()!=0) {
			model.addAttribute("postVoList", postVoList);
			model.addAttribute("targetPost", postVoList.get(0));
		}	

		return "blog/main";
	}
	
	@RequestMapping("/{categoryId}/{postId}")
	public String main(@PathVariable("id") String id, @PathVariable("categoryId") Long categoryId, @PathVariable("postId") Long postId, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(id);
		List<PostVo> postVoList = blogService.getPostsList(categoryId);
		PostVo postVo = blogService.getPost(categoryId, postId);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVoList", categoryVoList);
		model.addAttribute("postVoList", postVoList);
		model.addAttribute("targetPost", postVo);

		return "blog/main";
	}
	
	@RequestMapping("/admin")
	public String admin(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);

		return "blog/admin-default";
	}
	
	@RequestMapping("/admin/default/update")
	public String updateBlog(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("file") MultipartFile multipartFile) {
		String profile = fileUploadService.restore(multipartFile);
		BlogVo blogVo = new BlogVo();
		
		blogVo.setTitle(title);
		blogVo.setBlogId(id);
		
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		
		blogService.updateBlog(blogVo);
		
		// update servlet context bean
		servletContext.setAttribute("blog", blogVo);
		
		return "redirect:/{id}/admin";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);
		List<CategoryVo> list = blogService.getCategoriesList(id);
		
		for(CategoryVo cVo: list) {
			cVo.setPostNum(blogService.getPostsList(cVo.getId()).size());
		}
		
		model.addAttribute("list", list);

		return "blog/admin-category";
	}
	
	@RequestMapping("/admin/category/add")
	public String addCategory(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("description") String description) {
		CategoryVo vo = new CategoryVo();
		vo.setName(name);
		vo.setDescription(description);
		vo.setBlogId(id);
		
		blogService.addCategory(vo);

		return "redirect:/{id}/admin/category";
	}
	
	@RequestMapping("/admin/category/delete/{categoryId}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("categoryId") Long categoryId) {
		blogService.deletePosts(categoryId);
		blogService.deleteCategory(id, categoryId);

		return "redirect:/{id}/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVoList", categoryVoList);

		return "blog/admin-write";
	}
	
	@RequestMapping("/admin/write/add")
	public String addWrite(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("contents") String contents, @RequestParam("categoryId") String categoryId) {
		PostVo vo = new PostVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setCategoryId(Long.parseLong(categoryId));

		blogService.addWrite(vo);

		return "redirect:/{id}";
	}
	
}

