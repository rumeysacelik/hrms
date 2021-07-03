package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Talent;

public interface TalentService {
	DataResult<List<Talent>> getAll();
	
	Result add(Talent talent);

	Result update(Talent talent);
	
	DataResult<Talent> getById(int id);
}