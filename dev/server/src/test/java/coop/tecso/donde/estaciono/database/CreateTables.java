package coop.tecso.donde.estaciono.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CreateTables {

	public static void main(String[] args) throws IOException, SQLException {

		String resource = "coop/tecso/donde/estaciono/database/configDatabase.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		System.out.println("*---- Create Table Country -----*");
		session.update("create.tableCountry");
		System.out.println("============================================");

		System.out.println("*---- Create Table Province -----*");
		session.update("create.tableProvince");
		System.out.println("============================================");

		System.out.println("*---- Create Table Locality -----*");
		session.update("create.tableLocality");
		System.out.println("============================================");

		System.out.println("*---- Create Table Parking -----*");
		session.update("create.tableParking");
		System.out.println("============================================");

		System.out.println("*---- Create Table Coordinate -----*");
		session.update("create.tableCoordinate");
		System.out.println("============================================");

		System.out.println("*---- Create Table Email -----*");
		session.update("create.tableEmail");
		System.out.println("============================================");

		System.out.println("*---- Create Table Phone -----*");
		session.update("create.tablePhone");
		System.out.println("============================================");

		System.out.println("*---- Create Table Service -----*");
		session.update("create.tableService");
		System.out.println("============================================");

		System.out.println("*---- Create Table ParkingService -----*");
		session.update("create.tableParkingService");
		System.out.println("============================================");

		System.out.println("*---- Create Table VehicleType -----*");
		session.update("create.tableVehicleType");
		System.out.println("============================================");

		System.out.println("*---- Create Table TimeType -----*");
		session.update("create.tableTimeType");
		System.out.println("============================================");

		System.out.println("*---- Create Table FrequencyType -----*");
		session.update("create.tableFrequencyType");
		System.out.println("============================================");

		System.out.println("*---- Create Table PermissionAcction -----*");
		session.update("create.tablePermissionAcction");
		System.out.println("============================================");

		System.out.println("*---- Create Table Permission -----*");
		session.update("create.tablePermission");
		System.out.println("============================================");

		System.out.println("*---- Create Table UserType -----*");
		session.update("create.tableUserType");
		System.out.println("============================================");

		System.out.println("*---- Create Table User -----*");
		session.update("create.tableUser");
		System.out.println("============================================");

		System.out.println("*---- Create Table Login -----*");
		session.update("create.tableLogin");
		System.out.println("============================================");

		System.out.println("*---- Create Table UserPermission -----*");
		session.update("create.tableUserPermission");
		System.out.println("============================================");

		System.out.println("*---- Create Table UserHash -----*");
		session.update("create.tableUserHash");
		System.out.println("============================================");

		System.out.println("*---- Create Table PlaceAvailable -----*");
		session.update("create.tablePlaceAvailable");
		System.out.println("============================================");

		System.out.println("*---- Create Table ParkingRates -----*");
		session.update("create.tableParkingRates");
		System.out.println("============================================");

		System.out.println("*---- Create Table ParkedVehicle -----*");
		session.update("create.tableParkedVehicle");
		System.out.println("============================================");

		System.out.println("*---- Create Table MarketingType -----*");
		session.update("create.tableMarketingType");
		System.out.println("============================================");

		System.out.println("*---- Create Table Marketing -----*");
		session.update("create.tableMarketing");
		System.out.println("============================================");

		System.out.println("*---- Create Table Ticket -----*");
		session.update("create.tableTicket");
		System.out.println("============================================");

		System.out.println("*---- Create Table CashFlowType -----*");
		session.update("create.tableCashFlowType");
		System.out.println("============================================");

		System.out.println("*---- Create Table CashFlow -----*");
		session.update("create.tableCashFlow");
		System.out.println("============================================");

		session.close();
		
	}
}
