package coop.tecso.donde.estaciono.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.mongodb.config.MongoDBConfiguration;

public class MongoDBConectionTest {
	public static void main (String[] args) {		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
	    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	    
	    Login login = new Login(); //"gromero", "gromero");
	    login.setUser("jdelvalle");
		login.setPassword("jdelvalle");
		
	    //Save
	    mongoOperation.save(login);
	    
	    //Find
	    Login updatedLogin = mongoOperation.findOne(new Query(Criteria.where("user").is("gromero")), Login.class,"login");
	    
	    System.out.println("User: " + updatedLogin.getUser() + " Password: " + updatedLogin.getPassword());
	    
	    //Delete
	    mongoOperation.remove(new Query(Criteria.where("user").is("gromero")), "login");
	    
	    // List
	    List<Login> listLogin = mongoOperation.findAll(Login.class, "login");
		System.out.println("Number of login = " + listLogin.size());
	}
}
