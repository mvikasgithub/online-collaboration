package com.niitbejai.onlinecollaboration.initializer;
import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niitbejai.onlinecollaboration.config.MvcConfig;
import com.niitbejai.onlinecollaboration.filter.CORSfilter;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	// TODO Auto-generated method stub
    	return new Filter[] {new CORSfilter()};
    }

}