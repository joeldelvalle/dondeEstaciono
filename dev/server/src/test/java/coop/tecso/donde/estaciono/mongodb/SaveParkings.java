package coop.tecso.donde.estaciono.mongodb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import coop.tecso.donde.estaciono.model.AdditionalServices;
import coop.tecso.donde.estaciono.model.Coordinates;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Email;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.Phone;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.mongodb.config.MongoDBConfiguration;

public class SaveParkings {

	public void save_users_in_database() {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfiguration.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		Country c1 = new Country();
		c1.setName("Argentina");
		c1.setState("EN");
		c1.setStateDate(Calendar.getInstance());

		Province pro1 = new Province();
		pro1.setCountry(c1);
		pro1.setName("Bs As");
		pro1.setState("EN");
		pro1.setStateDate(Calendar.getInstance());

		Locality l1 = new Locality();
		l1.setName("Palermo");
		l1.setProvince(pro1);
		l1.setState("EN");
		l1.setStateDate(Calendar.getInstance());

		Email em1 = new Email();
		em1.setDescription("oficina comercial");
		em1.setMail("comercial@demo.com.ar");
		em1.setState("EN");
		em1.setStateDate(Calendar.getInstance());
		List<Email> emList = new ArrayList<Email>();
		emList.add(em1);

		Phone ph1 = new Phone();
		ph1.setDescription("Atencion al Cliente");
		ph1.setNumber("156489483");
		ph1.setState("EN");
		ph1.setStateDate(Calendar.getInstance());
		List<Phone> phList = new ArrayList<Phone>();
		phList.add(ph1);

		AdditionalServices ad1 = new AdditionalServices();
		ad1.setDescription("Actualizar Lugares Disponibles");
		ad1.setIdentificationCode("ALD");
		ad1.setPayment(false);
		ad1.setState("EN");
		ad1.setStateDate(Calendar.getInstance());
		List<AdditionalServices> adList = new ArrayList<AdditionalServices>();
		adList.add(ad1);
		
		
		Coordinates coo1 = new Coordinates();
		coo1.setLatitude(-34.6086555);
		coo1.setLongitude(-58.4406757);

		Parking p1 = new Parking();
		p1.setCoordinates(coo1);
		p1.setName("Estacionamiento Demo");
		p1.setAddress("Avenida Siempreviva 742");
		p1.setEmails(emList);
		p1.setAdditionalServices(adList);
		p1.setCountry(c1);
		p1.setIdentificationCode("ED01");
		p1.setLocality(l1);
		p1.setParkingRates(null);
		p1.setPhones(phList);
		p1.setProvince(pro1);
		p1.setState("EN");
		p1.setStateDate(Calendar.getInstance());
		p1.setTotalPlaces(15);
		
		
		Coordinates coo2 = new Coordinates();
		coo2.setLatitude(-34.6147495);
		coo2.setLongitude(-58.4462357);
		
		Parking p2 = new Parking();
		p2.setCoordinates(coo2);
		p2.setName("Estacionamiento Numero 2");
		p2.setAddress("Route 666");
		p2.setEmails(emList);
		p2.setAdditionalServices(adList);
		p2.setCountry(c1);
		p2.setIdentificationCode("EN02");
		p2.setLocality(l1);
		p2.setParkingRates(null);
		p2.setPhones(phList);
		p2.setProvince(pro1);
		p2.setState("EN");
		p2.setStateDate(Calendar.getInstance());
		p2.setTotalPlaces(15);
		
		
		Coordinates coo3 = new Coordinates();
		coo3.setLatitude(-34.6412605);
		coo3.setLongitude(-58.4029316);
		
		Parking p3 = new Parking();
		p3.setCoordinates(coo3);
		p3.setName("Estacionamiento Numero 3");
		p3.setAddress("JOJO 33");
		p3.setEmails(emList);
		p3.setAdditionalServices(adList);
		p3.setCountry(c1);
		p3.setIdentificationCode("EN03");
		p3.setLocality(l1);
		p3.setParkingRates(null);
		p3.setPhones(phList);
		p3.setProvince(pro1);
		p3.setState("EN");
		p3.setStateDate(Calendar.getInstance());
		p3.setTotalPlaces(15);

		// Save
		mongoOperation.save(p1);
		mongoOperation.save(p2);
		mongoOperation.save(p3);

		// Find
		Parking check1 = mongoOperation.findOne(new Query(Criteria.where("identificationCode").is(p1.getIdentificationCode())), Parking.class, "parking");
		Parking check2 = mongoOperation.findOne(new Query(Criteria.where("identificationCode").is(p2.getIdentificationCode())), Parking.class, "parking");
		Parking check3 = mongoOperation.findOne(new Query(Criteria.where("identificationCode").is(p2.getIdentificationCode())), Parking.class, "parking");


		Assert.assertNotNull(check1);
		Assert.assertNotNull(check2);
		Assert.assertNotNull(check3);
		
	}
	
}
