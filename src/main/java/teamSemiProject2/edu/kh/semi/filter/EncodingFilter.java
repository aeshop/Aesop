package teamSemiProject2.edu.kh.semi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 4.1 : 인코딩을 해주는 filter: 정제되지 않은 요청 응답을 형변환한 다음에 문자인코딩을 바꿔줌
 * filter, wrapper는 요청이 servlet에 들어오기 전/후에 거치는 곳이다.
 * 똑같이 어노테이션을 사용해서 이 곳을 먼저 거쳐라 등을 알려준다
 * 
 * 
 * */

@WebFilter(filterName = "encodingFilter",urlPatterns = {"/*"} )
public class EncodingFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//ServletRequest/response는 우리가 쓰는 httpServletRequest의 부모, 즉 형변환으로 다운캐스팅 필요하다.
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);//마지막으로 필터 체인에 추가해줌
		
	}



	@Override
	public void destroy() {

		
	}
	
	
}
