package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.TalentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.TalentDao;
import kodlamaio.hrms.entities.concretes.Talent;

@Service
public class TalentManager implements TalentService{

	private TalentDao talentDao;

	@Autowired
	public TalentManager(TalentDao talentDao) {
		super();
		this.talentDao = talentDao;
	}
	
	@Override
	public DataResult<List<Talent>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Talent>>(this.talentDao.findAll(),"Yetenekler Listelendi");
	}

	@Override
	public Result add(Talent talent) {
		talentDao.save(talent);
		return new SuccessDataResult("Yetenek başarıyla eklendi");
	}

	@Override
	public Result update(Talent talent) {
		Talent talentToUpdate = talentDao.getById(talent.getId());
		talentToUpdate = talent;
		talentDao.save(talentToUpdate);
		return new SuccessDataResult("Yetenek başarıyla güncellendi");
	}

	@Override
	public DataResult<Talent> getById(int id) {
	
		return new SuccessDataResult<Talent>(
				talentDao.getById(id)
				);
	}



}