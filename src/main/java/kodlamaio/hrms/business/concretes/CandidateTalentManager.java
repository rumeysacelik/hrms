package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateTalentService;
import kodlamaio.hrms.core.converters.DtoConverterService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateTalentDao;
import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.concretes.CandidateTalent;
import kodlamaio.hrms.entities.dtos.CandidateTalentDto;


@Service
public class CandidateTalentManager implements CandidateTalentService{
	
	private CandidateTalentDao candidateTalentDao;
	
	private DtoConverterService dtoConverterService;
	
	@Autowired
	public CandidateTalentManager(CandidateTalentDao candidateTalentDao,DtoConverterService dtoConverterService) {
		this.candidateTalentDao = candidateTalentDao;
		this.dtoConverterService = dtoConverterService;
	}
	

	@Override
	public DataResult<List<CandidateTalent>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<CandidateTalent>>(this.candidateTalentDao.findAll(),"Başarılı Şekilde İŞ Arayanın Yetenekleri Listelendi");
	}


	@Override
	public Result add(CandidateTalentDto talent) {
		// TODO Auto-generated method stub
		if(this.candidateTalentDao.existsCvIdAndTalentId(talent.getCvId(), talent.getTalentId()) != null) {
			return new ErrorResult("Daha önceden eklenmiş");
		}
		else {
			this.candidateTalentDao.save((CandidateTalent) this.dtoConverterService.dtoClassConverter(talent, CandidateTalent.class));
			return new SuccessResult("başarılı");
		}
	}


	@Override
	public DataResult<List<CandidateTalent>> findByCandidateId(int id) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<CandidateTalent>>(this.candidateTalentDao.findByCandidateCvId(id));
	}


	 @Override
	    public Result deleteTalent(int talentId) {
	        if(!this.candidateTalentDao.existsById(talentId)){
	            return new ErrorResult("Böyle bir teknoloji yok");
	        }
	        this.candidateTalentDao.deleteById(talentId);
	        return new SuccessResult("Silindi");
	    }


//	@Override
//	public Result update(CandidateTalentDto candidateTalent) {
//		// TODO Auto-generated method stub
//		CandidateTalent ref =  this.candidateTalentDao.findById(candidateTalent.get());
//		
//		if(candidateTalent.getTalentId() != null) {
//			ref.setTalentId(candidateTalent.getTalentId());
//		}
//		 if(candidateTalent.getEntryDate() != null) {
//			ref.setEntryDate(candidateTalent.getEntryDate());
//		}
//		
//		
//		 this.candidateTalentDao.save((CandidateTalent) dtoConverterService.dtoClassConverter(ref, CandidateTalent.class));
//		
//		return new SuccessResult("başarılı");
//	}	
	


}