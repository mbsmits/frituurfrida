package be.vdab.filters;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("*.htm")
public class RequestStatistiek2Filter implements Filter {
	
	private static final String STATISTIEK2 = "statistiek2";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		config.getServletContext().setAttribute(STATISTIEK2, new ConcurrentHashMap<String, AtomicInteger>());
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURI();
			int index = url.indexOf(";jsessionid=");
			if (index != -1) {
				url = url.substring(0, index);
			}
			@SuppressWarnings("unchecked")
			ConcurrentHashMap<String, AtomicInteger> statistiek2 = (ConcurrentHashMap<String, AtomicInteger>) request
					.getServletContext().getAttribute(STATISTIEK2);
			statistiek2.putIfAbsent(url, new AtomicInteger(0));
			AtomicInteger aantal = statistiek2.get(url);
			aantal.incrementAndGet();
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// Niets.
	}
	
}
