package ptithcm.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Manh
 */
public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]  {AppConfig.class,WebSecurityConfig.class };
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null; 
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}