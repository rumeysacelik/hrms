package kodlamaio.hrms.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateLanguage;
import kodlamaio.hrms.entities.concretes.CandidateSchool;
import kodlamaio.hrms.entities.dtos.CandidateLanguageDto;

@RestController
@RequestMapping("api/candidatelanguages")
@CrossOrigin
public class CandidateLanguagesController {


	private CandidateLanguageService candidateLanguageService;
	
	@Autowired
	public CandidateLanguagesController(CandidateLanguageService candidateLanguageService) {
		super();
		this.candidateLanguageService = candidateLanguageService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CandidateLanguage>> getAll(){
		return this.candidateLanguageService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CandidateLanguageDto lang) {
		// TODO Auto-generated method stub
		return this.candidateLanguageService.add(lang );
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody CandidateLanguageDto lang) {
		// TODO Auto-generated method stub
		return this.candidateLanguageService.update(lang);
	}
	
	@GetMapping("/findbycandidatecvid")
	public DataResult<List<CandidateLanguage>> findByCandidateId(@RequestParam int id) {
		// TODO Auto-generated method stub
		return this.candidateLanguageService.findByCandidateId(id);
	}
	 @DeleteMapping("/deleteLanguage")
	    public Result deleteLanguage(@RequestParam int languageId){
	        return this.candidateLanguageService.deleteLanguage(languageId);
	    }
	
}