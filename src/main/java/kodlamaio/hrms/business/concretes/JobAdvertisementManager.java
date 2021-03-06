package kodlamaio.hrms.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.Business.BusinessEngine;
import kodlamaio.hrms.core.converters.DtoConverterService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
	
	private JobAdvertisementDao jobAdvertisementDao;

	private EmployerDao employerDao;
	
	private CityDao cityDao;
	

	private DtoConverterService dtoConverterService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,EmployerDao employerDao, CityDao cityDao,DtoConverterService dtoConverterService) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerDao = employerDao;
		this.cityDao = cityDao;
		this.dtoConverterService = dtoConverterService;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAll(),"data listelendi");
		
	}

	@Override
	public Result add(JobAdvertisementDto jobAdvertisement) {
		
		Result engine = BusinessEngine.run(
				findEmployer(jobAdvertisement),
				findCity(jobAdvertisement),
				descriptionNullChecker(jobAdvertisement),
				ifMinSalaryNull(jobAdvertisement),
				ifMaxSalaryNull(jobAdvertisement),
				minSalaryChecker(jobAdvertisement),
				maxSalaryChecker(jobAdvertisement),
				 ifMinSalaryEqualsMaxSalary(jobAdvertisement) ,
				 ifQuotaSmallerThanOne(jobAdvertisement),
				 appealExpirationChecker( jobAdvertisement)
				);
		if(!engine.isSuccess()) {
			return new ErrorResult(engine.getMessage());
		}
		this.jobAdvertisementDao.save((JobAdvertisement) dtoConverterService.dtoClassConverter(jobAdvertisement, JobAdvertisement.class));
		return new SuccessResult("eklendi");
		
	
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActive() {
		// TODO Auto-generated method stub
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActive(true),"Ba??ar??l??");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveSorted() {
		// TODO Auto-generated method stub
		return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByIsActiveOrderByCreatedDateDesc(true),"Ba??ar??l??");
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id) {
		// TODO Auto-generated method stub
		if(!this.employerDao.existsById(id)) {
			return new ErrorDataResult("Hata: ???? veren bulunamad??");
		}
		else {
			return new SuccessDataResult <List<JobAdvertisement>>(this.jobAdvertisementDao.getEmployersActiveJobAdvertisement(id),"Ba??ar??l??");
		}
	}

	@Override
	public DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id) {
		// TODO Auto-generated method stub
		if(!this.jobAdvertisementDao.existsById(id)) {
			return new ErrorDataResult("Hata: ???? veren bulunamad??");
		}
		JobAdvertisement ref =  this.jobAdvertisementDao.getOne(id);
		ref.setActive(false);
		return new SuccessDataResult <JobAdvertisement>(this.jobAdvertisementDao.save(ref),"???? ??lan?? Pasif olarak ayarland??");
		
	}
	

	
	private Result findEmployer(JobAdvertisementDto jobAdvertisement) {
		if(!this.employerDao.existsById(jobAdvertisement.getEmployerId())) {
			return new ErrorResult("???? veren bulunamad??");
		}
		return new SuccessResult();
	}
	
	
	private Result findCity(JobAdvertisementDto jobAdvertisement) {
		if(!this.cityDao.existsById(jobAdvertisement.getCityId())) {
			return new ErrorResult("??ehir bulunamad??");
		}
		return new SuccessResult();
	}
	
	private Result descriptionNullChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getDescription().isEmpty()) {
			return new ErrorResult("???? Tan??m?? Bo?? B??rak??lamaz");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == null) {
			return new ErrorResult("Minimum Maa?? Girilmek Zorundad??r");
		}
		return new SuccessResult();
	}
	
	
	private Result ifMaxSalaryNull(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == null) {
			return new ErrorResult("Maksimum Maa?? Girilmek Zorundad??r");
		}
		return new SuccessResult();
	}
	
	private Result minSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() == 0) {
			return new ErrorResult("Minimum Maa?? 0 verilemez");
		}
		return new SuccessResult();
	}
	
	private Result maxSalaryChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMaxSalary() == 0) {
			return new ErrorResult("Maksimum Maa?? 0 verilemez");
		}
		return new SuccessResult();
	}
	
	private Result ifMinSalaryEqualsMaxSalary(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getMinSalary() >= jobAdvertisement.getMaxSalary()) {
			return new ErrorResult("Minimum Maa?? Maksimum Maa??a e??it olamaz");
		}
		return new SuccessResult();
	}
	
	private Result ifQuotaSmallerThanOne(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getQuota() < 1) {
			return new ErrorResult("A????k pozisyon adedi 1 den k??????k olamaz");
		}
		return new SuccessResult();
	}
	
	private Result appealExpirationChecker(JobAdvertisementDto jobAdvertisement) {
		if(jobAdvertisement.getAppealExpirationDate() == null) {
			return new ErrorResult("Son Ba??vuru Tarihi Girilmek Zorundad??r");
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getConfirmedJobAdvertisements(),"Ba??ar??l?? ??ekilde Onaylanm???? ???? ??lanlari Listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getWaitingJobAdvertisements() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getWaitingJobAdvertisements(),"Ba??ar??l?? ??ekilde Onaylanm???? ???? ??lanlari Listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getOneJobAds(int id) {
		// TODO Auto-generated method stub
		return  new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getOneById(id),"Ba??ar??l?? ??ekilde ???? ??lani Detay?? Geldi");
	}

	@Override
	public Result confirmJobAd(int id) {
		// TODO Auto-generated method stub
		if(this.jobAdvertisementDao.existsById(id)) {
			
			JobAdvertisement ref = this.jobAdvertisementDao.getOne(id);
			
			ref.setConfirmed(true);
			
			jobAdvertisementDao.save(ref);
			
			return  new SuccessResult("Ba??ar??l?? ??ekilde ???? ??lani  HRMS Personeli Taraf??ndan Onayland??");
		}
		return new ErrorResult("???? ??lani Bulunamad??");
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> getConfirmedJobAdvertisementsWithPageable(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getConfirmedJobAdvertisements(pageable));
	}
	
	


}