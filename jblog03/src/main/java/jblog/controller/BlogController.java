package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.service.BlogService;
import jblog.vo.BlogVo;

@Controller
public class BlogController {
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService=blogService;
	}
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);

		return "blog/main";
	}
	
	@RequestMapping({"/{id}/admin", "/{id}/admin/default"})
	public String admin(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);

		return "blog/admin-default";
	}
	
	@RequestMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);

		return "blog/admin-category";
	}
	
	@RequestMapping("/{id}/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getContents(id);
		model.addAttribute("blogVo", vo);

		return "blog/admin-write";
	}
}

