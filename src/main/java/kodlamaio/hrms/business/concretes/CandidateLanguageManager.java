package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateLanguageService;
import kodlamaio.hrms.core.converters.DtoConverterService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateLanguageDao;
import kodlamaio.hrms.entities.concretes.CandidateLanguage;
import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;
import kodlamaio.hrms.entities.dtos.CandidateSchoolDto;

@Service
public class CandidateLanguageManager implements CandidateLanguageService{
	


	private CandidateLanguageDao candidateLanguageDao;
	private DtoConverterService dtoConverterService;
	
	@Autowired
	public CandidateLanguageManager(CandidateLanguageDao candidateLanguageDao,DtoConverterService dtoConverterService) {
		super();
		this.candidateLanguageDao = candidateLanguageDao;
		this.dtoConverterService = dtoConverterService;
	}

	@Override
	public DataResult<List<CandidateLanguage>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<CandidateLanguage>>(this.candidateLanguageDao.findAll(),"Başarılı");
	}

	@Override
	public Result add(CandidateLanguageDto lang) {
		// TODO Auto-generated method stub
		if(this.candidateLanguageDao.existsCvIdAndLangId(lang.getCandidateCvId(), lang.getLanguageId()) != null) {
			return new ErrorResult("Daha önceden eklenmiş");
		}
		this.candidateLanguageDao.save((CandidateLanguage) dtoConverterService.dtoClassConverter(lang, CandidateLanguage.class));
		return new SuccessResult("Başarılı");
	}

	@Override
	public Result update(CandidateLanguageDto lang) {
		// TODO Auto-generated method stub
		CandidateLanguage ref = this.candidateLanguageDao.findById(lang.getId() );
		ref.setLevel(lang.getLevel());
		ref.setLanguagesName(lang.getLanguagesName());
			
		 this.candidateLanguageDao.save((CandidateLanguage )
				 dtoConverterService.dtoClassConverter(ref, CandidateLanguage.class));
		
		return new SuccessResult("başarılı");	
		
		}
	
//	@Override
//	public Result update(int cvId, int langId,int level) {
//		// TODO Auto-generated method stub
//		CandidateLanguage ref = this.candidateLanguageDao.getByCandidateCvIdAndLanguageId(cvId,langId);
//		ref.setLevel(level);
//		this.candidateLanguageDao.save(ref);
//		return new SuccessResult(""+ref.getLevel());
//	}

	@Override
	public DataResult<List<CandidateLanguage>> findByCandidateId(int id) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<CandidateLanguage>>(this.candidateLanguageDao.findByCandidateCvId(id));
	}
	
	 @Override
	    public Result deleteLanguage(int languageId) {
	        if(!this.candidateLanguageDao.existsById(languageId)){
	            return new ErrorResult("Böyle bir dil bulunamadı");
	        }
	        this.candidateLanguageDao.deleteById(languageId);
	        return new SuccessResult("Silindi");
	    }


}