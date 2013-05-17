package coop.tecso.donde.estaciono.dao.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class DaoGeneric {

	@Autowired
	MongoOperations mongoTemplate;

	public MongoOperations getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
