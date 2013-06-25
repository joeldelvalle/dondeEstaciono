package coop.tecso.donde.estaciono.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coop.tecso.donde.estaciono.dao.CountryDao;
import coop.tecso.donde.estaciono.dao.LocalityDao;
import coop.tecso.donde.estaciono.dao.ProvinceDao;
import coop.tecso.donde.estaciono.exception.DondeEstacionoServerException;
import coop.tecso.donde.estaciono.logger.CustomLogger;
import coop.tecso.donde.estaciono.model.Country;
import coop.tecso.donde.estaciono.model.Locality;
import coop.tecso.donde.estaciono.model.Province;
import coop.tecso.donde.estaciono.service.ApplicationService;

/**
 * 
 * @author jdelvalle
 * 
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	private CustomLogger log = new CustomLogger(getClass().getCanonicalName());

	@Autowired
	private CountryDao countryDao;

	@Autowired
	private ProvinceDao provinceDao;

	@Autowired
	private LocalityDao localityDao;

	@Override
	public List<Country> getCountryList() throws DondeEstacionoServerException {
		String method = "getCountryList";
		log.logStartMethod(method);

		List<Country> countryList = new ArrayList<Country>();
		countryList.addAll(this.countryDao.findAll());

		log.logInfo(method, "country list size: " + countryList.size());
		log.logEndMethod(method);
		return countryList;
	}

	@Override
	public List<Province> getProvinceList() throws DondeEstacionoServerException {
		String method = "getProvinceList";
		log.logStartMethod(method);

		List<Province> provinceList = new ArrayList<Province>();

		provinceList.addAll(this.provinceDao.findAll());

		log.logInfo(method, "province list size: " + provinceList.size());
		log.logEndMethod(method);
		return provinceList;
	}

	@Override
	public List<Province> getProvinceListByCountry(Integer countryId) throws DondeEstacionoServerException {
		String method = "getProvinceListByCountry";
		log.logStartMethod(method);

		List<Province> provinceList = new ArrayList<Province>();

		provinceList.addAll(this.provinceDao.findByCountryId(countryId));

		log.logInfo(method, "province list size: " + provinceList.size());
		log.logEndMethod(method);
		return provinceList;
	}

	@Override
	public List<Locality> getLocalityList() throws DondeEstacionoServerException {
		String method = "getLocalityList";
		log.logStartMethod(method);

		List<Locality> localityList = new ArrayList<Locality>();

		localityList.addAll(this.localityDao.findAll());

		log.logInfo(method, "locality list size: " + localityList.size());
		log.logEndMethod(method);
		return localityList;
	}

	@Override
	public List<Locality> getLocalityListByProvince(Integer provinceId) throws DondeEstacionoServerException {
		String method = "getLocalityListByProvince";
		log.logStartMethod(method);

		List<Locality> localityList = new ArrayList<Locality>();

		localityList.addAll(this.localityDao.findByProvince(provinceId));

		log.logInfo(method, "locality list size: " + localityList.size());
		log.logEndMethod(method);
		return localityList;
	}

	@Override
	public List<Locality> getLocalityListByProvinceByCountry(Integer provinceId, Integer countryId) throws DondeEstacionoServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
