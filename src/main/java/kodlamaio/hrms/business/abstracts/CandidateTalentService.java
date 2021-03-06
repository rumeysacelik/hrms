package kodlamaio.hrms.business.abstracts;


import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.concretes.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateJobExperienceDto;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;

public interface CandidateTalentService {
DataResult<List<CandidateTalent>> getAll();
	
	Result add(CandidateTalentDto talent);
	
	DataResult<List<CandidateTalent>> findByCandidateId(int id);
	
	public Result deleteTalent(int technologyId);
	
//	Result update(CandidateTalentDto candidateTalent);
}