package cn.edu.hbcit.smms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 登录管理
 * 登录验证过滤器
 * @author 李玮
 * @version 2012-06-01
 */
public class LoginFilter implements Filter {

	 public static final String LOGIN_PAGE = "/index.jsp";
	 public static final String LOGOUT_PAGE = "/index.jsp";
	 public static final String[] EXCEPT_PAGE = {"index.jsp","index.jsp"};//不参与验证的页面
	 protected final Logger log = Logger.getLogger(LoginFilter.class.getName());
	 public void destroy() {
	    }
	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
	            FilterChain filterChain) throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) servletRequest;
	        /**
	         * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
	         * 无法得到的方法，就要把此request对象构造成HttpServletRequest
	         */
	        HttpServletResponse response = (HttpServletResponse) servletResponse;

	        String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:

	        HttpSession session = request.getSession(true);
	       
	        boolean bool = false;
	        for (int i = 0; i< EXCEPT_PAGE.length; i++){
	            if (currentURL.indexOf(EXCEPT_PAGE[i])>=0){
	                bool = true;
	                break;
	            }
	        }
	        if (currentURL.indexOf(LOGIN_PAGE) == -1 && currentURL.indexOf(LOGOUT_PAGE) == -1 && currentURL.indexOf(".jsp") > -1 && !bool) {
	            // 判断当前页是否是重定向以后的登录页面，如果是就不做session的判断，防止出现死循环
	            String ref = request.getHeader("REFERER");  //是否是从地址栏直接输入的地址吗？
	            log.debug("request头信息：" + ref);
	            if (session == null || session.getAttribute("username") == null || session.getAttribute("username").equals("") || (ref==null) || (ref.equals(""))) {
	                log.debug("非法登录，转向登录页面...");
	            	response.sendRedirect(request.getContextPath() + LOGOUT_PAGE);
	                return;
	            }
	        }
	        // 加入filter链继续向下执行
	        filterChain.doFilter(request, response);
	    }

	    public void init(FilterConfig arg0) throws ServletException {

	    }
	
}
