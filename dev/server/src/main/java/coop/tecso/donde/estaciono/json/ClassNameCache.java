package coop.tecso.donde.estaciono.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class ClassNameCache {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private static ClassNameCache instance;

	private Map<String, Class<? extends Object>> classNameCache;

	public static ClassNameCache getInstance() {

		if (DESUtils.isNull(instance)) {
			instance = new ClassNameCache();
		}

		return instance;
	}

	private ClassNameCache() {
		this.classNameCache = new HashMap<String, Class<? extends Object>>();
		loadClasses();
	}

	private void loadClasses() {
		String method = "loadClasses";
		log.logStartMethod(method);

		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("coop.tecso.donde.estaciono.communication.model"))
													.include(FilterBuilder.prefix("coop.tecso.donde.estaciono.model"))));

		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);

		for (Class<? extends Object> clazz : classes) {

			this.classNameCache.put(clazz.getSimpleName().toLowerCase(), clazz);

		}
		
		this.addJavaClasses();

		log.logInfo(method, "se cargaron las clases del modelo");
		log.logEndMethod(method);
	}

	private void addJavaClasses() {
		this.classNameCache.put("arraylist", ArrayList.class);
		this.classNameCache.put("list", List.class);
		this.classNameCache.put("hashmap", HashMap.class);
		this.classNameCache.put("map", Map.class);
	}

	public Class<? extends Object> getClassFromCache(String className) {
		return this.classNameCache.get(className);
	}

}
