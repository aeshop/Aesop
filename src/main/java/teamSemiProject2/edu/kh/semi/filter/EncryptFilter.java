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

import teamSemiProject2.edu.kh.semi.wrapper.EncryptWrapper;




/**
 * @author nyr
 *5. 암호화를 해주는 역할의 필터이다. 암호화를 위해선 받아온 parameter의 값을 암호화해야하는데, 필터에는 그런 기능이 없다.
 *req의 오버라이딩은 wrapper에서 가능하고 wapper를 filter에서 호출하는 형식이다
 *
 */
@WebFilter(filterName = "encryptFilter", urlPatterns = {"/member/login"})
public class EncryptFilter implements Filter {
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("암호화 필터 생성");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//wrapper로 가져온 parameter를 사용해야한다. 
		
		if(req.getMethod().equals("POST")) {//post로 온 요청이면 wrapper 씌워서 받고, 아니면 그냥 받고
			
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			
			chain.doFilter(encWrapper, resp);
		} else {
			chain.doFilter(req, resp);
			
		}
		
	}
	@Override
	public void destroy() {
		System.out.println("암호화 필터 파괴 후 재생성");

	}



}
