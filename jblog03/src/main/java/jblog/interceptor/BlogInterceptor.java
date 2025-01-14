package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.service.BlogService;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

public class BlogInterceptor implements HandlerInterceptor {
	private BlogService blogService;
	
	public BlogInterceptor(BlogService blogService) {
		this.blogService=blogService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// blog main 저장
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		BlogVo blogVo=(BlogVo)request.getServletContext().getAttribute("blog");
		if(blogVo==null) {
			blogVo=blogService.getContents(userVo.getId());
			request.getServletContext().setAttribute("blog",blogVo);
		}
		
		return true;
	}

}
