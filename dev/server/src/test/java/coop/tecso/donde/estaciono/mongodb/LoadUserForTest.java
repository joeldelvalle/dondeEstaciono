package coop.tecso.donde.estaciono.mongodb;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.mongodb.config.MongoDBConfiguration;

public class LoadUserForTest {

	@Test
	public void save_users_in_database() {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		Login login1 = new Login();
		login1.setUser("gromero");
		login1.setPassword("gromero");
		login1.setIdUser(1L);
		
		Login login2 = new Login();
		login2.setUser("jdelvalle");
		login2.setPassword("jdelvalle");
		login2.setIdUser(2L);
		
		Login login3 = new Login();
		login3.setUser("nalderete");
		login3.setPassword("nalderete");
		login3.setIdUser(3L);

		// Save
		mongoOperation.save(login1);
		mongoOperation.save(login2);
		mongoOperation.save(login3);

		// Find
		Login checkLogin1 = mongoOperation.findOne(new Query(Criteria.where("idUser").is(login1.getIdUser())), Login.class, "login");
		Login checkLogin2 = mongoOperation.findOne(new Query(Criteria.where("idUser").is(login2.getIdUser())), Login.class, "login");
		Login checkLogin3 = mongoOperation.findOne(new Query(Criteria.where("idUser").is(login3.getIdUser())), Login.class, "login");


		Assert.assertNotNull(checkLogin1);
		Assert.assertNotNull(checkLogin2);
		Assert.assertNotNull(checkLogin3);
		
	}

}
