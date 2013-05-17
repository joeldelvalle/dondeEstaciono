package coop.tecso.donde.estaciono.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.Phone;
import coop.tecso.donde.estaciono.mongodb.config.MongoDBConfiguration;

public class MongoDBTest {
	
	public static void main (String[] args) {		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
	    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	    
	    List<MobileHash> permisos = mongoOperation.findAll(MobileHash.class);
	    System.out.println("Number of parking = " + permisos.size());
	    
	    for (MobileHash parking : permisos) {
	    	System.out.println(parking.getId() +  "   " + parking.getHash());
			
		}
	    

	    
		
				
//		mongoOperation.findAndRemove(new Query(Criteria.where("id").is(parking.getId())), Parking.class);
		
		
		
	}
	
}
