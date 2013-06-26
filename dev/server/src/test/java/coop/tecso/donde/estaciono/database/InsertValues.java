package coop.tecso.donde.estaciono.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Login;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.model.TimeType;
import coop.tecso.donde.estaciono.model.User;
import coop.tecso.donde.estaciono.model.UserType;

public class InsertValues {

	public static void main(String[] args) throws IOException, SQLException {

		String resource = "coop/tecso/donde/estaciono/database/configDatabase.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();
		
		
		System.out.println("*---- Insert Registros en Country -----*");
		session.insert("insert.insertDatosCountry", getCountry());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Province -----*");
		session.insert("insert.insertDatosProvince", getProvince());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Locality -----*");
		session.insert("insert.insertDatoslocality", getLocality());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Parking -----*");
		session.insert("insert.insertDatosParking", getParking());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en UserType -----*");
		session.insert("insert.insertDatosUserType", getUserType());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en User 1 -----*");
		session.insert("insert.insertDatosUser", getUser_1());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en User 2 -----*");
		session.insert("insert.insertDatosUser", getUser_2());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en User 3 -----*");
		session.insert("insert.insertDatosUser", getUser_3());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Login 1 -----*");
		session.insert("insert.insertDatosLogin", getLogin_1());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Login 2 -----*");
		session.insert("insert.insertDatosLogin", getLogin_2());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en Login 3 -----*");
		session.insert("insert.insertDatosLogin", getLogin_3());
		System.out.println("============================================");

		System.out.println("*---- Insert Registros en TimeType 1 -----*");
		session.insert("insert.insertDatosTimeType", getTimeType_1());
		System.out.println("============================================");
		
		System.out.println("*---- Insert Registros en TimeType 2 -----*");
		session.insert("insert.insertDatosTimeType", getTimeType_2());
		System.out.println("============================================");
		
		session.commit();
		
	}
	
	private static Country getCountry() {
		Country cc = new Country();
		cc.setId(1L);
		cc.setName("Argentina");
		cc.setState("EN");
		cc.setStateDate(getCalendar());
		
		return cc;
	}
	
	private static Province getProvince() {
		Province pp = new Province();
		pp.setId(1L);
		pp.setCountry(getCountry());
		pp.setName("Buenos Aires");
		pp.setState("EN");
		pp.setStateDate(getCalendar());
		
		return pp;
	}
	
	private static Locality getLocality() {
		Locality ll = new Locality();
		ll.setId(1L);
		ll.setName("Palermo");
		ll.setProvince(getProvince());
		ll.setState("EN");
		ll.setStateDate(getCalendar());
		
		return ll;
	}
	
	private static Parking getParking() {
		Parking pp = new Parking();
		pp.setAddress("av siempre viva 1230");
		pp.setId(1L);
		pp.setIdentificationCode("OTT");
		pp.setLocality(getLocality());
		pp.setName("Lo de Otto");
		pp.setState("EN");
		pp.setStateDate(getCalendar());
		pp.setTotalPlaces(15);
		
		return pp;
	}
	
	private static UserType getUserType() {
		UserType uu = new UserType();
		uu.setId(1L);
		uu.setDescription("superAdmin");
		
		return uu;
	}
	
	private static User getUser_1() {
		User us = new User();
		us.setEmail("joel.delvalle@tecso.coop");
		us.setId(1L);
		us.setName("joel");
		us.setNumberIdentification("31252804");
		us.setParking(getParking());
		us.setPhone("13456");
		us.setState("EN");
		us.setStateDate(getCalendar());
		us.setUserType(getUserType());
		
		return us;
	}
	
	private static User getUser_2() {
		User us = new User();
		us.setEmail("german.romero@tecso.coop");
		us.setId(2L);
		us.setName("german");
		us.setNumberIdentification("13216548");
		us.setParking(getParking());
		us.setPhone("65487");
		us.setState("EN");
		us.setStateDate(getCalendar());
		us.setUserType(getUserType());
		
		return us;
	}
	
	private static User getUser_3() {
		User us = new User();
		us.setEmail("nicolas.alderete@tecso.coop");
		us.setId(3L);
		us.setName("nicolas");
		us.setNumberIdentification("98764611");
		us.setParking(getParking());
		us.setPhone("79879");
		us.setState("EN");
		us.setStateDate(getCalendar());
		us.setUserType(getUserType());
		
		return us;
	}
	
	private static Login getLogin_1() {
		Login ll = new Login();
		ll.setId(1L);
		ll.setParking(getParking());
		ll.setPassword("jdelvalle");
		ll.setState("EN");
		ll.setStateDate(getCalendar());
		ll.setUser(1L);
		ll.setUserName("jdelvalle");
		
		return ll;		
	}
	
	private static Login getLogin_2() {
		Login ll = new Login();
		ll.setId(2L);
		ll.setParking(getParking());
		ll.setPassword("gromero");
		ll.setState("EN");
		ll.setStateDate(getCalendar());
		ll.setUser(2L);
		ll.setUserName("gromero");
		
		return ll;		
	}
	
	private static Login getLogin_3() {
		Login ll = new Login();
		ll.setId(3L);
		ll.setParking(getParking());
		ll.setPassword("nalderete");
		ll.setState("EN");
		ll.setStateDate(getCalendar());
		ll.setUser(3L);
		ll.setUserName("nalderete");
		
		return ll;		
	}
	
	private static TimeType getTimeType_1() {
		TimeType tt = new TimeType();
		tt.setId(1);
		tt.setDescription("Horas");
		return tt;
	}
	
	private static TimeType getTimeType_2() {
		TimeType tt = new TimeType();
		tt.setId(2);
		tt.setDescription("Minutos");
		return tt;
	}
	
	private static Date getCalendar() {
		return Calendar.getInstance().getTime();
	}
	
}
