package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateLanguage;
import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

public interface CandidateLanguageService{
DataResult<List<CandidateLanguage>> getAll();
	
	Result add(CandidateLanguageDto lang);
	
	Result update(CandidateLanguageDto lang);
	
	DataResult<List<CandidateLanguage>> findByCandidateId(int id);
	
	public Result deleteLanguage(int languageId);
	
//	Result updateLanguage(CandidateLanguageDto candidatelanguage);
}