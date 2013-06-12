package coop.tecso.donde.estaciono.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class DatabaseConnection {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	private static DatabaseConnection instance;

	private SqlSessionFactory sqlSessionFactory;

	private DatabaseConnection() throws DondeEstacionoServerException {
		try {

			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			this.sqlSessionFactory.getConfiguration().addMappers("coop.tecso.donde.estaciono.dao.queries");

		} catch (IOException e) {
			log.logError("DatabaseConnection", "no se encontro la configuracion de la base de datos", e);
			throw new DondeEstacionoServerException("database.config.not.found", e);
		}

	}

	public static DatabaseConnection getInstance() throws DondeEstacionoServerException {

		if (DESUtils.isNull(instance)) {
			instance = new DatabaseConnection();
		}

		return instance;
	}

	public SqlSession getSession() {

		return sqlSessionFactory.openSession();
	}
	
	private void loadMappers() {
		
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("coop.tecso.donde.estaciono.communication.model"))
													.include(FilterBuilder.prefix("coop.tecso.donde.estaciono.model"))));

		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);

		for (Class<? extends Object> clazz : classes) {


		}
		
	}

}
