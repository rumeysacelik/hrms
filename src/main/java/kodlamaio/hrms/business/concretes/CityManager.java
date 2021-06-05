package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.Business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService{
	
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		//Result engine = BusinessEngine.run(null);
		return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Şehirler başarıyla listelendi");
	}

	@Override
	public DataResult<City> add(City city) {

		Result engine = BusinessEngine.run(cityNameChecker(city));
		if(!engine.isSuccess()) {
			return new ErrorDataResult(null,engine.getMessage());
		}
		return new SuccessDataResult<City>(this.cityDao.save(city),"Başarıyla eklendi");
	}
	
	private Result cityNameChecker(City city) {
		if(city.getCityName().isEmpty() || city.getCityName().isBlank()) {
			return new ErrorResult("Şehir adı girilmek zorundadır");
		}
		return new SuccessResult();
	}
	

}