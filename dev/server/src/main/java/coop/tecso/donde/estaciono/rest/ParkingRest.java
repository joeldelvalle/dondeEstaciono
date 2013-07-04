package coop.tecso.donde.estaciono.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import coop.tecso.donde.estaciono.communication.DESRequest;
import coop.tecso.donde.estaciono.communication.DESResponse;
import coop.tecso.donde.estaciono.errors.ErrorBuilder;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Coordinate;
import coop.tecso.donde.estaciono.model.Parking;
import coop.tecso.donde.estaciono.rest.security.SecureRest;
import coop.tecso.donde.estaciono.service.ParkingService;
import coop.tecso.donde.estaciono.utils.DESConstants;
import coop.tecso.donde.estaciono.utils.DESUtils;

/**
 * 
 * @author joel.delvalle
 * 
 *         restful para la solicitud de estacionamientos. ya sea para la web o
 *         para mobile
 */
@Component
@Path("/parking")
public class ParkingRest extends SecureRest {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private ParkingService parkingService;

	/**
	 * rest para solicitar el listado de todos los estacionamientos registrados,
	 * solo se utiliza desde la web
	 * 
	 * @param json
	 * @return
	 */
	@POST
	@Path("/publicParkingList")
	@Consumes(MediaType.APPLICATION_JSON)
	public String publicParkingList(String json) {
		String method = "publicParkingList";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidationsForPublicAccess(request);

			List<Parking> parkingList = this.parkingService.findAllParking();

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(parkingList);

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.ERROR);
			dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError(e.getMessage()));
		}

		String jsonResponse = DESUtils.convertObjectToJson(dondeEstacionoResponse);
		log.logEndMethod(method);

		return jsonResponse;
	}

	/**
	 * rest para solicitar el listado de todos los estacionamientos registrados,
	 * partiendo desde la ubicacion (coordenadas) del mobile
	 * 
	 * @param json
	 * @return
	 */
	@POST
	@Path("/parkingList/mobileCoordinates")
	@Consumes(MediaType.APPLICATION_JSON)
	public String parkingListByMobileCoordinates(String json) {
		String method = "parkingListByMobileCoordinates";
		log.logStartMethod(method);

		DESResponse dondeEstacionoResponse = new DESResponse();

		DESRequest request = null;
		try {

			request = DESUtils.convertJsonToObject(json, DESRequest.class);

			this.securityValidationsForMobileAccess(request);

			Coordinate coordinates = request.getPayload(Coordinate.class);

			List<Parking> parkingList = this.parkingService.findParkingByCoordinates(coordinates);

			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.SUCCESS);
			dondeEstacionoResponse.setPayload(parkingList);

		} catch (DondeEstacionoServerException e) {
			log.logError(method, "ERROR FALTAL", e);
			dondeEstacionoResponse.setStatus(DESConstants.StatusResponse.ERROR);
			dondeEstacionoResponse.setPayload(ErrorBuilder.getInstance().buildError(e.getMessage()));
		}

		String jsonResponse = DESUtils.convertObjectToJson(dondeEstacionoResponse);
		log.logEndMethod(method);

		return jsonResponse;
	}

	private void securityValidationsForPublicAccess(DESRequest request) throws DondeEstacionoServerException {
		String method = "securityValidationsForPublicAccess";
		log.logStartMethod(method);

		this.publicWebHashValidate(request);
		this.macValidation(request);

		log.logEndMethod(method);
	}

	private void securityValidationsForMobileAccess(DESRequest request) throws DondeEstacionoServerException {
		String method = "securityValidationsForMobileAccess";
		log.logStartMethod(method);

		this.mobileClientHashValidate(request);
		this.macValidation(request);

		log.logEndMethod(method);
	}

}
