package be.vdab.listeners;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public final class RequestStatistiekListener implements ServletContextListener, ServletRequestListener {
	
	private static final String			STATISTIEK				= "statistiek";
	private static final Set<String>	UITGESLOTEN_EXTENSIES	= new CopyOnWriteArraySet<>(
			Arrays.asList("png", "gif", "jpg", "css", "js", "ico"));
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setAttribute(STATISTIEK, new ConcurrentHashMap<String, AtomicInteger>());
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			String url = request.getRequestURI();
			boolean match = UITGESLOTEN_EXTENSIES.stream().anyMatch(extensie -> url.endsWith("." + extensie));
			
			if (!match) {
				@SuppressWarnings("unchecked")
				Map<String, AtomicInteger> map = (Map<String, AtomicInteger>) event.getServletContext()
						.getAttribute(STATISTIEK);
				AtomicInteger aantal = map.get(url);
				
				aantal.incrementAndGet();
			}
			
		}
		
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		// Niets.
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Niets.
	}
	
}
