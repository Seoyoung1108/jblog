package jblog.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {
	private BlogService blogService;
	
	public BlogInterceptor(BlogService blogService) {
		this.blogService=blogService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// blog main 저장
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println(pathVariables);
   
		BlogVo blogVo=blogService.getContents(pathVariables.get("id"));
		request.getServletContext().setAttribute("blog",blogVo);
		
		return true;
	}

}
