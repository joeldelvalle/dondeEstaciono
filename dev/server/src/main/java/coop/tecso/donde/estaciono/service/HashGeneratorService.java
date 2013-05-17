package coop.tecso.donde.estaciono.service;

import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.model.ParkingHash;

/**
 * 
 * @author joel.delvalle
 * 
 */
public interface HashGeneratorService {

	public MobileHash generateMobileHash(MobileRegister mobileRegister);

	public ParkingHash generateParkingHas(ParkingHash parkingHash);

}
