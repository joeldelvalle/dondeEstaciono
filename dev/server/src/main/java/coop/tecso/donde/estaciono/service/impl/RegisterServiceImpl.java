package coop.tecso.donde.estaciono.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import coop.tecso.donde.estaciono.dao.MobileHashDao;
import coop.tecso.donde.estaciono.dao.RegisterDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.LoggerFactory;
import coop.tecso.donde.estaciono.model.MobileHash;
import coop.tecso.donde.estaciono.model.MobileRegister;
import coop.tecso.donde.estaciono.service.HashGeneratorService;
import coop.tecso.donde.estaciono.service.RegisterService;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class RegisterServiceImpl implements RegisterService {

	LoggerFactory log = LoggerFactory.getInstance(getClass());

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
