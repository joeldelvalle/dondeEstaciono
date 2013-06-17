package coop.tecso.donde.estaciono.cache.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import coop.tecso.donde.estaciono.cache.ClassNameCache;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class CacheLoader implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ClassNameCache.getInstance().initialize();
	}

}
