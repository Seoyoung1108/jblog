package jblog.interceptor;

import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

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
		String id = request.getParameter("id");
        
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println(pathVariables);
   
		BlogVo blogVo=blogService.getContents(pathVariables.get("id"));
		request.getServletContext().setAttribute("blog",blogVo);
		
		return true;
	}

}
