package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.MobileHashDao;
import coop.tecso.donde.estaciono.dao.RegisterDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.service.HashGeneratorService;
import coop.tecso.donde.estaciono.service.RegisterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private RegisterDao registerDao;

	@Autowired
	private MobileHashDao mobileHashDao;

	@Autowired
	private HashGeneratorService hashGeneratorService;

	@Override
	public MobileHash mobileRegister(MobileRegister mobileRegister) throws DondeEstacionoServerException {
		String method = "mobileRegister";
		log.logStartMethod(method);

		log.logInfo(method, "save mobile in database");
		this.registerDao.save(mobileRegister);

		log.logInfo(method, "generate hash to mobile identify");
		MobileHash hash = this.hashGeneratorService.generateMobileHash(mobileRegister);

		log.logInfo(method, "sava mobile hash in database");
		this.mobileHashDao.save(hash);

		log.logEndMethod(method);
		return hash;
	}

}
