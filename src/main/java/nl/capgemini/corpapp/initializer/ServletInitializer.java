/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package nl.capgemini.corpapp.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import nl.capgemini.corpapp.config.ApplicationConfig;
import nl.capgemini.corpapp.filter.CorsFilter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * @author Dave Syer
 * 
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	  protected Class<?>[] getRootConfigClasses() {
	    return new Class[] {ApplicationConfig.class};
	  }
	 
	  @Override
	  protected Class<?>[] getServletConfigClasses() {
	    return new Class[] {ApplicationConfig.class};
	  }
	 
	  @Override
	  protected String[] getServletMappings() {
	    return new String[]{"/"};
	  }
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		super.onStartup(servletContext);
		
		ServletRegistration.Dynamic jersey = servletContext.addServlet("JerseyServlet", new SpringServlet());
		jersey.setLoadOnStartup(1);
		jersey.setInitParameter("com.sun.jersey.config.property.packages", "nl.capgemini.corpapp.rest");
		jersey.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		jersey.addMapping("/rest/*");
		
		CorsFilter corsFilter = new CorsFilter();
		servletContext.addFilter("corsFilter", corsFilter).addMappingForUrlPatterns(null, false, "/*");
		
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
		
		

	}
	
	
}
