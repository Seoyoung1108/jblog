package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jblog.service.BlogService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;
import jblog.security.Auth;
import jblog.security.AuthUser;
import jblog.service.FileuploadService;
import jblog.service.UserService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	private final UserService userService;
	private final FileuploadService fileUploadService;
	private final BlogService blogService;
	private final ServletContext servletContext;
	
	public BlogController(BlogService blogService, FileuploadService fileUploadService, UserService userService, ServletContext servletContext) {
		this.blogService=blogService;
		this.fileUploadService=fileUploadService;
		this.userService=userService;
		this.servletContext = servletContext;
	}
	
	@RequestMapping({"","/{path1}","/{path1}/{path2}"})
	public String main(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1, @PathVariable("path2") Optional<Long> path2, Model model) {
		// 존재하지 않는 블로그 처리
		if(userService.getUser(id)==null) {
			return "redirect:/";
		}
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
			
			// 존재하지 않는 카테고리 & 글 처리
			if(blogService.getCategory(id, categoryId)==null) {
				return "redirect:/{id}";
			} else if(blogService.getPost(id, categoryId, postId)==null) {
				return "redirect:/{id}";
			}
		} else if(path1.isPresent()) {
			categoryId = path1.get();
			
			// 존재하지 않는 카테고리 처리
			if(blogService.getCategory(id, categoryId)==null) {
				return "redirect:/{id}";
			}
		}
		
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(id);
		List<PostVo> postVoList = blogService.getPostsList(id, categoryId);
		PostVo postVo = blogService.getPost(id, categoryId, postId);
	
		model.addAttribute("categoryVoList", categoryVoList);
		model.addAttribute("postVoList", postVoList);
		model.addAttribute("targetPost", postVo);

		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/admin")
	public String admin(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		// 다른 유저 접근 제한
		if(!authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		
		return "blog/admin-default";
	}
	
	@Auth
	@RequestMapping("/admin/default/update")
	public String updateBlog(@AuthUser UserVo authUser, @PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("file") MultipartFile multipartFile) {
		String profile = fileUploadService.restore(multipartFile);
		BlogVo blogVo = new BlogVo();
		
		blogVo.setTitle(title);
		blogVo.setBlogId(authUser.getId());
		
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		
		blogService.updateBlog(blogVo);
		
		// update servlet context bean
		servletContext.setAttribute("blog", blogVo);
		
		return "redirect:/{id}/admin";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		// 다른 유저 접근 제한
		if(!authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		
		List<CategoryVo> list = blogService.getCategoriesList(authUser.getId());
		
		for(CategoryVo cVo: list) {
			cVo.setPostNum(blogService.getPostsList(authUser.getId(), cVo.getId()).size());
		}
		
		model.addAttribute("list", list);

		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping("/admin/category/add")
	public String addCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("description") String description) {
		CategoryVo vo = new CategoryVo();
		vo.setName(name);
		vo.setDescription(description);
		vo.setBlogId(authUser.getId());
		
		blogService.addCategory(vo);

		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{categoryId}")
	public String deleteCategory(@AuthUser UserVo authUser, @PathVariable("id") String id, @PathVariable("categoryId") Long categoryId) {
		blogService.deletePosts(categoryId);
		blogService.deleteCategory(authUser.getId(), categoryId);

		return "redirect:/{id}/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@AuthUser UserVo authUser, @PathVariable("id") String id, Model model) {
		// 다른 유저 접근 제한
		if(!authUser.getId().equals(id)) {
			return "redirect:/{id}";
		}
		
		List<CategoryVo> categoryVoList = blogService.getCategoriesList(authUser.getId());
		
		model.addAttribute("categoryVoList", categoryVoList);

		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping("/admin/write/add")
	public String addWrite(@AuthUser UserVo authUser, @PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("contents") String contents, @RequestParam("categoryId") String categoryId) {
		PostVo vo = new PostVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setCategoryId(Long.parseLong(categoryId));

		blogService.addWrite(vo);

		return "redirect:/{id}";
	}
	
}

