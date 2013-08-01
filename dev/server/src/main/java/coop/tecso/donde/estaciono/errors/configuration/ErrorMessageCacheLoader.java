package coop.tecso.donde.estaciono.errors.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import coop.tecso.donde.estaciono.errors.ErrorMessageCache;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ErrorMessageCacheLoader implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ErrorMessageCache.getInstance().initialize();
	}

}
