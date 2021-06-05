package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
DataResult<List<JobAdvertisement>> getAll();
	
	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> findAllByIsActive();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveSorted();
	
	DataResult<List<JobAdvertisement>> findAllByIsActiveAndCompanyName(int id);
	
	DataResult<JobAdvertisement> setJobAdvertisementDisabled(int id);
	
}