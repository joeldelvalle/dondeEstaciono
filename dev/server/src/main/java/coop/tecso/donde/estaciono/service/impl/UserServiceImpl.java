package coop.tecso.donde.estaciono.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.AdditionalServices;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Email;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.Permission;
import coop.tecso.donde.estaciono.model.Phone;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.model.User;
import coop.tecso.donde.estaciono.model.UserType;
import coop.tecso.donde.estaciono.service.UserService;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class UserServiceImpl implements UserService {

	private LoggerFactory log = LoggerFactory.getInstance(this.getClass());

	@Override
	public User findMockUser() {
		String method = "findMockUser";
		log.logStartMethod(method);

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

		Parking est = new Parking();
		est.setName("Estacionamiento Demo");
		est.setAddress("Avenida Siempreviva 742");
		est.setEmails(emList);
		est.setAdditionalServices(adList);
		est.setCountry(c1);
		est.setIdentificationCode("ED01");
		est.setLocality(l1);
		est.setParkingRates(null);
		est.setPhones(phList);
		est.setProvince(pro1);
		est.setState("EN");
		est.setStateDate(Calendar.getInstance());
		est.setTotalPlaces(15);

		Permission permiso1 = new Permission();
		permiso1.setCode("PER1");
		permiso1.setDescription("Permiso Uno");
		permiso1.setState("EN");
		permiso1.setStateDate(Calendar.getInstance());

		Permission permiso2 = new Permission();
		permiso2.setCode("PER2");
		permiso2.setDescription("Permiso Dos");
		permiso2.setState("EN");
		permiso2.setStateDate(Calendar.getInstance());

		Permission permiso3 = new Permission();
		permiso3.setCode("PER3");
		permiso3.setDescription("Permiso Tres");
		permiso3.setState("DI");
		permiso3.setStateDate(Calendar.getInstance());

		List<Permission> permisoList = new ArrayList<Permission>();
		permisoList.add(permiso1);
		permisoList.add(permiso2);
		permisoList.add(permiso3);

		UserType ust = new UserType();
		ust.setName("Super-Admin");
		ust.setState("EN");
		ust.setStateDate(Calendar.getInstance());

		User user = new User();
		user.setName("Dr. Emmett Brown");
		user.setParking(est);
		user.setPermission(permisoList);
		user.setState("EN");
		user.setStateDate(Calendar.getInstance());
		user.setEmail("usuario.est.demo@demo.com.ar");
		user.setUserType(ust);

		log.logEndMethod(method);
		return user;
	}

}
