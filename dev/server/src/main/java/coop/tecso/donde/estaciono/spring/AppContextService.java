package coop.tecso.donde.estaciono.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.bean.common.GenericBean;
import coop.tecso.donde.estaciono.cache.ClassNameCache;

/**
 * 
 * @author joel.delvalle
 * 
 *         clase que se encarga de buscar beans dentro del contexto de spring
 */
@Component
public class AppContextService {

	@Autowired
	ApplicationContext appSpringContext;

	public GenericBean getCustomBean(String beanId) {

		Class<? extends Object> clazz = ClassNameCache.getInstance().getClassFromCache(beanId);

		return GenericBean.class.cast(this.appSpringContext.getBean(clazz));

	}

	public <T> T getBean(String beanId, Class<T> cls) {
		if (this.appSpringContext != null) {
			Object bean = this.appSpringContext.getBean(beanId, cls);
			try {
				return cls.cast(bean);
			} catch (ClassCastException ce) {
				assert (false);
				return null;
			}
		} else {
			return null;
		}
	}

}
