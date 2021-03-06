package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.concretes.CandidateTalent;

public interface CandidateTalentDao extends JpaRepository<CandidateTalent, Integer>{
	@Query(value="select * from candidates_talent where candidates_cv_id =:cvId and talents_id=:talentId  LIMIT 1 ",nativeQuery=true)
	CandidateTalent existsCvIdAndTalentId(int cvId, int talentId);
	
	List<CandidateTalent> findByCandidateCvId(int id);
	
	@Query(value="select * from candidates_cv_talent where id =:id  ",nativeQuery=true)
	CandidateTalent findById(int id);
	
}
